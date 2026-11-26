package bo.edu.uajms.cristianalejandro.surmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class CartItemExamen(
    val name: String,
    val unitPrice: String,
    val subtotal: String,
    val quantity: Int
)

class FragmentCart : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = "Carrito de Compras"

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.menu_cart_toolbar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_clear_cart -> {
                        Toast.makeText(requireContext(), "Vaciar carrito", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)

        val rcvProducts = view.findViewById<RecyclerView>(R.id.RCV_FRGCart_Products)
        val btnFinishPurchase = view.findViewById<Button>(R.id.BTN_FRGCart_FinishPurchase)

        val cartItems = listOf(
            CartItemExamen("Té Verde Mate", "Precio: 4.99 Bs.", "4.99 Bs.", 1),
            CartItemExamen("Fideo", "Precio: 2.50 Bs.", "5.00 Bs.", 2),
            CartItemExamen("Dulce de Leche", "Precio: 3.75 Bs.", "3.75 Bs.", 1)
        )

        rcvProducts.layoutManager = LinearLayoutManager(requireContext())
        rcvProducts.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_cart_product, parent, false)
                return object : RecyclerView.ViewHolder(itemView) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val item = cartItems[position]
                holder.itemView.findViewById<TextView>(R.id.TXV_ITMCartProduct_Name).text = item.name
                holder.itemView.findViewById<TextView>(R.id.TXV_ITMCartProduct_UnitPrice).text = item.unitPrice
                holder.itemView.findViewById<TextView>(R.id.TXV_ITMCartProduct_Subtotal).text = item.subtotal
                holder.itemView.findViewById<TextView>(R.id.TXV_ITMCartProduct_Quantity).text = item.quantity.toString()

                holder.itemView.findViewById<ImageButton>(R.id.IMB_ITMCartProduct_Plus).setOnClickListener {
                    Toast.makeText(requireContext(), "mas", Toast.LENGTH_SHORT).show()
                }

                holder.itemView.findViewById<ImageButton>(R.id.IMB_ITMCartProduct_Minus).setOnClickListener {
                    Toast.makeText(requireContext(), "menos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun getItemCount(): Int = cartItems.size
        }

        btnFinishPurchase.setOnClickListener {
            Toast.makeText(requireContext(), "compra finalizada", Toast.LENGTH_SHORT).show()
        }

        val scrollView = view.findViewById<ScrollView>(R.id.SCV_FRGCart_Container)
        scrollView.post {
            scrollView.scrollTo(0, 0)
        }
    }
}