FROM python:3.12-slim

WORKDIR /app

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        gcc \
        build-essential \
        libjpeg-dev \
        zlib1g-dev \
        libpng-dev \
        libfreetype6-dev \
        liblcms2-dev \
        libopenjp2-7-dev \
        libtiff-dev \
        libwebp-dev \
        tcl-dev \
        tk-dev \
        python3-tk \
    && pip install --upgrade pip

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt && \
    apt-get remove -y gcc build-essential && \
    apt-get autoremove -y #&&
    #rm -rf /var/lib/apt/lists/*

COPY . /app

ENTRYPOINT ["mitmdump", "-s", "mittproxy.py", "--mode", "regular", "--listen-port", "9650"]
