FROM postgres:15-alpine

ENV POSTGRES_USER=postgres_user
ENV POSTGRES_PASSWORD=postgres_password
ENV POSTGRES_DB=sales

EXPOSE 5432