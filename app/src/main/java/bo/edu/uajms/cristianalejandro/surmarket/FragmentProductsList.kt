package bo.edu.uajms.cristianalejandro.surmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uajms.cristianalejandro.surmarket.models.product
import kotlin.Int
import kotlin.String

class FragmentProductsList : Fragment(){
    private lateinit var IMV_FRGProductsList_ShoppingCart: ImageView
    private lateinit var IMV_FRGProductsList_Search: ImageView
    private lateinit var ETX_FRGProductsList_Search: EditText
    private lateinit var BTN_FRGProductsList_AddProduct: Button
    private lateinit var RCV_FRGProductsList_ProductsList: RecyclerView
    private lateinit var adapterProducts: AdapterProducts
    private var listaProductos = mutableListOf<product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView(view)
        configureListener()
        configureRecyclerView()
        loadSampleProducts()
    }

    private fun initializeView(view: View) {
        IMV_FRGProductsList_ShoppingCart=view.findViewById(R.id.IMV_FRGProductsList_ShoppingCart)
        IMV_FRGProductsList_Search=view.findViewById(R.id.IMV_FRGProductsList_Search)
        ETX_FRGProductsList_Search=view.findViewById(R.id.ETX_FRGProductsList_Search)
        BTN_FRGProductsList_AddProduct=view.findViewById(R.id.BTN_FRGProductsList_AddProduct)
        RCV_FRGProductsList_ProductsList=view.findViewById(R.id.RCV_FRGProductsList_ProductsList)
    }
    private fun configureListener(){
        BTN_FRGProductsList_AddProduct.setOnClickListener(){
            Toast.makeText(requireContext(), getString(R.string.addProduct), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_products_list_to_add_product)

        }
    }
    private fun configureRecyclerView(){
        adapterProducts = AdapterProducts(
            productsList = listaProductos,
            clickOnBuy = { product->
                Toast.makeText(requireContext(), getString(R.string.addCart)+ "${product.name}",
                    Toast.LENGTH_SHORT).show()
            }
        )
        RCV_FRGProductsList_ProductsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterProducts
        }
    }
    private fun loadSampleProducts(){
        val productsSample = listOf(
            product(
                id= 1,
                name= "Camisa",
                category= "Ropa de Varon",
                price= 180.0,
                imageUrl = "",
                description= "Camisa de varon color blanco talle M",
                available= true,
                stock= 1
            ),
            product(
                id= 1,
                name= "Pantalon",
                category= "Ropa de Varon",
                price= 160.0,
                imageUrl = "",
                description= "Pantalon jeans talle 50",
                available= true,
                stock= 1
            ),
            product(
                id= 1,
                name= "Remera",
                category= "Ropa de Varon",
                price= 130.0,
                imageUrl = "",
                description= "Remera color negro talle P",
                available= true,
                stock= 1
            ),
            product(
                id= 1,
                name= "Zapatillas",
                category= "Ropa de Varon",
                price= 530.0,
                imageUrl = "",
                description= "zapatilla blanca marca Adidas",
                available= true,
                stock= 1
            ),
            product(
                id= 1,
                name= "Zapatos",
                category= "Ropa de Varon",
                price= 450.0,
                imageUrl = "",
                description= "Zapato negro de cuerro genuino",
                available= true,
                stock= 1
            )
        )
        listaProductos.clear()
        listaProductos.addAll(productsSample)
        adapterProducts.notifyChanged()
    }

}