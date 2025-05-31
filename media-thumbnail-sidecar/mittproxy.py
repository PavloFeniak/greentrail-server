from mitmproxy import http
from image_processor import generate_thumbnail
from requests_toolbelt.multipart import decoder, encoder

def request(flow: http.HTTPFlow) -> None:
    print("lets-go")
    if flow.request.method != "POST" or "/multipart-upload" not in flow.request.path:
        return
    content_type = flow.request.headers.get("content-type", "")
    if "multipart/form-data" not in content_type:
        return

    multipart_data = decoder.MultipartDecoder(flow.request.raw_content, content_type)

    # Поля, які очікує сервер
    original = None
    file_name = None
    mime_type = None
    related_type = None
    related_id = None

    # Витягуємо дані з multipart
    for part in multipart_data.parts:
        content_disposition = part.headers.get(b"Content-Disposition", b"").decode()

        if 'name="multipartFile"' in content_disposition:
            original = part.content
        elif 'name="fileName"' in content_disposition:
            file_name = part.text
        elif 'name="mimeType"' in content_disposition:
            mime_type = part.text
        elif 'name="relatedType"' in content_disposition:
            related_type = part.text
        elif 'name="relatedId"' in content_disposition:
            related_id = part.text

    if not original:
        return  # без файлу нема чого обробляти

    # Створюємо thumbnail
    thumbnail_bytes = generate_thumbnail(original)

    # Створюємо новий multipart-form-data з усіма полями
    fields = {
        "original": ("original.jpg", original, mime_type or "image/jpeg"),
        "thumbnail": ("thumbnail.jpg", thumbnail_bytes, "image/jpeg"),
        "fileName": file_name or "default.jpg",
        "mimeType": mime_type or "image/jpeg",
        "relatedType": related_type or "unknown",
        "relatedId": str(related_id) if related_id else "0",
    }

    multipart_encoder = encoder.MultipartEncoder(fields=fields)

    # Заміна тіла запиту та заголовків
    flow.request.headers["Content-Type"] = multipart_encoder.content_type
    flow.request.content = multipart_encoder.to_string()
