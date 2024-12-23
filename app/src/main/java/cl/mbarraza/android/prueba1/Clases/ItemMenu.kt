package cl.mbarraza.android.prueba1.Clases

class ItemMenu(
    val nombre: String,
    val precio: Int // Aquí usaremos un `Int` para el precio, ya que será un número y más fácil de manejar para cálculos
) {
    // Constructor para inicializar el ItemMenu con nombre y precio
    constructor(nombre: String, precio: String) : this(nombre, precio.toInt())
    // Si el precio se recibe como String, lo convertimos a Int
}
