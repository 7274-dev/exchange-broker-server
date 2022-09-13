package dev.the.mag.exchangebrokerbackend.services

import dev.the.mag.exchangebrokerbackend.dto.ExchangeItemDto
import dev.the.mag.exchangebrokerbackend.exceptions.NoSuchItemException
import dev.the.mag.exchangebrokerbackend.models.ExchangeItem
import dev.the.mag.exchangebrokerbackend.models.toDto
import dev.the.mag.exchangebrokerbackend.repositories.ExchangeItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemService(
    @Autowired
    private val itemRepository: ExchangeItemRepository
) {

    // TODO: Maybe add convenience functions to change just one filed of the item?
    // TODO: Add constrains to be <80%
    fun createItem(ownerId: Long, exchangeId: Long, name: String, desc: String, price: Double, earningPercent: Int, giveToCharity: Boolean): ExchangeItemDto {
        val model = ExchangeItem(-1, ownerId, exchangeId, name, desc, price, soldFor = null, pending = false, earningPercent, giveToCharity, recall = false)
        itemRepository.save(model)
        return model.toDto()
    }

    fun getItem(itemId: Long): ExchangeItem? {
        return itemRepository.findByIdOrNull(itemId)
    }

    fun deleteItem(itemId: Long) {
        val item = itemRepository.findByIdOrNull(itemId) ?: throw NoSuchItemException()
        itemRepository.delete(item)
    }

    fun itemSold(itemId: Long, soldFor: Double) {
        var item = itemRepository.findByIdOrNull(itemId) ?: throw NoSuchItemException()
        item.soldFor = soldFor
        itemRepository.save(item)
    }

    fun recallItem(itemId: Long) {
        var item = itemRepository.findByIdOrNull(itemId) ?: throw NoSuchItemException()
        item.recall = true
        itemRepository.save(item)
    }

    /**
     * @param itemId - ID of the item
     * @param approved - the approval status of the item true => approved / false => !approved
     */

    fun approveItem(itemId: Long, approved: Boolean) {
        var item = itemRepository.findByIdOrNull(itemId) ?: throw NoSuchItemException()
        item.pending = approved
        itemRepository.save(item)
    }

    /**
     * @param itemId - ID of the item to be edited
     * @param newItem - A DTO of the new item
     * @return true if transaction was successful, false if not (when the item was already approved by owner)
     */
    fun editItem(itemId: Long, newItem: ExchangeItemDto): Boolean {

        var item = itemRepository.findByIdOrNull(itemId) ?: throw NoSuchItemException()

        if (item.pending == null || item.pending == true) {

            item.name = newItem.name
            item.desc = newItem.desc
            item.price = newItem.price

            // Sold for and pending are omitted

            item.earningPercent = newItem.earningPercent
            item.giveToCharity = newItem.giveToCharity

            item.recall = newItem.recall

            itemRepository.save(item)
            // OP successful
            return true
        }
        // OP unsuccessful - already approved
        return false
    }
}
