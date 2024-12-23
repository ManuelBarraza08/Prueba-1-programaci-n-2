package cl.mbarraza.android.prueba1.Clases

class ItemMenu(
    val nombre: String,
    val precio: Int
) {
    constructor(nombre: String, precio: String) : this(nombre, precio.toInt())

}
