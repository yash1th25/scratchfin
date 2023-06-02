FROM python:3.8-slim-buster
ENV PYTHONUNBUFFERED=1
WORKDIR /app

COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt

COPY testscripts.py testscripts.py
COPY data.csv data.csv

EXPOSE 5000
CMD ["python3", "testscripts.py"]
