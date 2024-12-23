package cl.mbarraza.android.prueba1.Clases

class ItemMesa(
    val itemMenu: ItemMenu,
    var cantidad: Int
) {
    fun calcularSubtotal(): Int {
        return itemMenu.precio * cantidad
    }
}