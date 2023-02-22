package abstractfactory

class ProductServiceRestImpl: IProductService {
    private val products = listOf("Teclado", "Mouse", "Monitor")
    override fun getProducts(): List<String> {
        return this.products
    }
}