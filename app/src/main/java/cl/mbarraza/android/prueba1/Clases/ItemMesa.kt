package cl.mbarraza.android.prueba1.Clases

class ItemMesa(
    val itemMenu: ItemMenu,
    var cantidad: Int // Cantidad de platillos pedidos
) {
    // Calcula el subtotal de un platillo basado en la cantidad
    fun calcularSubtotal(): Int {
        return itemMenu.precio * cantidad
    }
}