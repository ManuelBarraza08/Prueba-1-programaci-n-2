package cl.mbarraza.android.prueba1.Clases

class CuentaMesa(
    val mesa: Int, // Identificador de la mesa
    val items: MutableList<ItemMesa> = mutableListOf(),
    var aceptarPropina: Boolean = true
) {

    // Constructor adicional para agregar un ItemMesa directamente
    constructor(mesa: Int, itemMesa: ItemMesa) : this(mesa, mutableListOf(itemMesa))

    // Agregar un ItemMenu con su cantidad (crea un nuevo ItemMesa)
    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val itemMesa = ItemMesa(itemMenu, cantidad)
        items.add(itemMesa)
    }

    // Agregar un ItemMesa ya creado
    fun agregarItem(itemMesa: ItemMesa) {
        items.add(itemMesa)
    }

    // Calcula el total sin la propina
    fun calcularTotalSinPropina(): Int {
        var totalSinPropina = 0
        items.forEach {
            totalSinPropina += it.calcularSubtotal()
        }
        return totalSinPropina
    }

    // Calcula la propina (10% del total sin propina si está habilitada)
    fun calcularPropina(): Int {
        return if (aceptarPropina) {
            (calcularTotalSinPropina() * 0.10).toInt()
        } else {
            0
        }
    }

    // Calcula el total con propina (total sin propina + propina)
    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
}
}