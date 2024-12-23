package cl.mbarraza.android.prueba1


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.mbarraza.android.clases.ItemMenu
import cl.mbarraza.android.clases.ItemMesa
import cl.mbarraza.android.clases.CuentaMesa
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var pastelDeChocloCantidad: EditText
    private lateinit var cazuelaCantidad: EditText
    private lateinit var propinaSwitch: Switch
    private lateinit var subtotalTextView: TextView
    private lateinit var propinaTextView: TextView
    private lateinit var totalTextView: TextView

    private val pastelDeChoclo = ItemMenu("Pastel de Choclo", 12000)
    private val cazuela = ItemMenu("Cazuela", 10000)

    // Se crea la cuenta para la mesa 1 (mesa: 1)
    private val cuentaMesa = CuentaMesa(1)

    private val formatoPesos: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pastelDeChocloCantidad = findViewById(R.id.etPastelCantidad)
        cazuelaCantidad = findViewById(R.id.etCazuelaCantidad)
        propinaSwitch = findViewById(R.id.switchPropina)
        subtotalTextView = findViewById(R.id.tvSubtotal)
        propinaTextView = findViewById(R.id.tvPropina)
        totalTextView = findViewById(R.id.tvTotal)

        // Agregamos los platillos a la cuenta inicial
        cuentaMesa.agregarItem(pastelDeChoclo, 0)
        cuentaMesa.agregarItem(cazuela, 0)

        pastelDeChocloCantidad.addTextChangedListener(cantidadWatcher(pastelDeChoclo))
        cazuelaCantidad.addTextChangedListener(cantidadWatcher(cazuela))
        propinaSwitch.setOnCheckedChangeListener { _, isChecked ->
            cuentaMesa.aceptarPropina = isChecked
            actualizarTotales()
        }
    }

    private fun cantidadWatcher(itemMenu: ItemMenu): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val cantidad = s?.toString()?.toIntOrNull() ?: 0
                val itemMesa = cuentaMesa.items.find { it.itemMenu == itemMenu }
                itemMesa?.cantidad = cantidad
                actualizarTotales()
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun actualizarTotales() {
        val totalSinPropina = cuentaMesa.calcularTotalSinPropina()
        val propina = cuentaMesa.calcularPropina()
        val totalConPropina = cuentaMesa.calcularTotalConPropina()

        subtotalTextView.text = formatoPesos.format(totalSinPropina)
        propinaTextView.text = formatoPesos.format(propina)
        totalTextView.text = formatoPesos.format(totalConPropina)
    }
}