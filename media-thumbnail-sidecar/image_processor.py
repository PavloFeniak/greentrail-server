from PIL import Image
import io

def generate_thumbnail(image_bytes: bytes, size=(128, 128)) -> bytes:
    image = Image.open(io.BytesIO(image_bytes))
    image.thumbnail(size)
    buf = io.BytesIO()
    image.save(buf, format="JPEG")
    return buf.getvalue()

def optimize_image(image_bytes: bytes) -> bytes:
    image = Image.open(io.BytesIO(image_bytes))
    buf = io.BytesIO()
    image.save(buf, format="JPEG", optimize=True, quality=85)
    return buf.getvalue()
