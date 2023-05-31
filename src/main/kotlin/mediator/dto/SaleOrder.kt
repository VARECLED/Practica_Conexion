package mediator.dto

import mediator.dto.Sale

class SaleOrder(var id: String) : Sale() {

    @JvmName("getId1")
    fun getId(): String {
        return id
    }

    @JvmName("setId1")
    fun setId(id: String) {
        this.id = id
    }
}