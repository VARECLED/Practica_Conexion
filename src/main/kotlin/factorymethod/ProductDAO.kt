package factorymethod

import java.sql.Connection
import java.sql.PreparedStatement

class ProductDAO() {
    private val dbAdapter: IDBAdapter? = DBFactory().defaultDBAdapter

    fun findAllProducts(): List<Product>? {
        val connection: Connection? = dbAdapter!!.getConnection()
        val productList: MutableList<Product> = ArrayList()
        try {
            val statement: PreparedStatement = connection!!.prepareStatement(
                "SELECT product_id, description, price " +
                        "  FROM product"
            )
            val results = statement.executeQuery()
            while (results.next()) {
                productList.add(
                    Product(
                        results.getLong(1),
                        results.getString(2), results.getDouble(3)
                    )
                )
            }
            return productList
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            try {
                connection!!.close()
            } catch (e: Exception) {
            }
        }
    }

    fun saveProduct(product: Product): Boolean {
        val connection: Connection? = dbAdapter!!.getConnection()
        try {
            val statement: PreparedStatement = connection!!
                .prepareStatement(
                    ("INSERT INTO Productos(idProductos,"
                            + "productName, productPrice) VALUES (?,?,?)")
                )
            statement.setString(2, product.description)
            statement.setDouble(3, product.price)
            statement.executeUpdate()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            try {
                connection!!.close()
            } catch (e: Exception) {
            }
        }
    }
}