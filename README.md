# Prueba Backend IP

Backend - Spring boot

### Endpoints

* POST /trace. Recibe una dirección IP y retorna información del país (lenguaje, moneda, husos horarios, distancia desde Buenos Aires, etc)

```
https://ejercicio-be-ip.herokuapp.com/be/trace (heroku)
http://localhost:7070/be/trace (docker)

Body: {
	"ip": "87.255.159.255"
}

```

Salida

```
{
    "ip": "87.255.159.255",
    "date": "13/02/2021 03:17:49",
    "country": "France (france)",
    "iso_code": "fr",
    "languages": [
        "français (fr)"
    ],
    "currency": "EUR (1 EUR = 1.212050 USD)",
    "times": [
        "05:17:49 (UTC-10:00)",
        "05:47:49 (UTC-09:30)",
        "06:17:49 (UTC-09:00)",
        "07:17:49 (UTC-08:00)",
        "11:17:49 (UTC-04:00)",
        "12:17:49 (UTC-03:00)",
        "04:17:49 (UTC+01:00)",
        "06:17:49 (UTC+03:00)",
        "07:17:49 (UTC+04:00)",
        "08:17:49 (UTC+05:00)",
        "02:17:49 (UTC+11:00)",
        "03:17:49 (UTC+12:00)"
    ],
    "estimatedDistance": "10811 kms"
}

```

* GET /stats. Retorna la mayor y menor distancia a Buenos Aires en base a las consultas previas y un promedio de todas las invocaciones.

```
https://ejercicio-be-ip.herokuapp.com/be/stats (heroku)
http://localhost:7070/be/stats (docker)

```

Salida

```
{
    "greaterDistance": "521 kms",
    "shorterDistance": "18497 kms",
    "averageTotal": "8059 kms"
}

```

### Docker

Bajar imagenes

```bash
docker pull maven
docker pull openjdk
```
Clonar el repositorio y ubicarse en el directorio

```bash
cd ejercicio-be-ip/
```

Ejecutar Dockerfile

```bash
docker build -t ejercicio-be .
```

Ejecutar contenedor

```bash
docker run -d -p 7070:8080 --name backend-ip ejercicio-be

```
