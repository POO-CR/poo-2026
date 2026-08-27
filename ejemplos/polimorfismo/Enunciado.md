9. Defina la clase TarifaProveedor con un método calcular(totalSMS, totalMinutos,
totalGigas) que, dado la cantidad de mensajes, minutos de llamada y GB de consumo de
datos calcule el valor en pesos a pagar. El valor a retornar del método calcular debe ser
la suma de los resultados obtenidos en los métodos calcularSMS(totalSMS),
calcularMinutosDeLlamada(totalMinutos) y calcularConsumoGB(totalGigas)
Los valores por defecto de cada servicio son
● Mensajes de texto(SMS): $1
● Minuto de llamada: $15
● Gigas(GB) de internet: $20.
Además de los métodos anteriores, debe poseer un método abstracto getNombre() que
retorne el nombre del proveedor.
Luego, defina una clase hija por cada uno de los siguientes proveedores:
● Claro: que tiene un 20% extra sobre el básico total
● Personal: que tiene un 20% extra sobre los minutos de llamada y 50% sobre los
GB de datos.PROGRAMACIÓN ORIENTADA A OBJETOS
TRABAJO PRÁCTICO N°3
● Movistar: tiene un 10% extra sobre los mensajes de texto, 20% sobre las
llamadas y 30% sobre los GB de datos.
Desarrolle un ingreso por teclado que permita ingresar la cantidad de SMS, minutos de
llamada, Gigas y muestre como resultado la tarifa que se obtendría con cada proveedor.