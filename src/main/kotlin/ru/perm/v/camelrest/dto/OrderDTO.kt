package ru.perm.v.camelrest.dto

import java.math.BigDecimal

class OrderDTO {
    var id = -1L
    var name = ""
    var price = BigDecimal(0.00)

    constructor()

    constructor(id: Long, name: String, price: BigDecimal) {
        this.id = id
        this.name = name
        this.price = price
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderDTO

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}