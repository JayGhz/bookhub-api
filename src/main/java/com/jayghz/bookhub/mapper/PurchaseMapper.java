package com.jayghz.bookhub.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.jayghz.bookhub.dto.PurchaseCreateDTO;
import com.jayghz.bookhub.dto.PurchaseDTO;
import com.jayghz.bookhub.dto.PurchaseItemCreateDTO;
import com.jayghz.bookhub.dto.PurchaseItemDTO;
import com.jayghz.bookhub.model.entity.Book;
import com.jayghz.bookhub.model.entity.Purchase;
import com.jayghz.bookhub.model.entity.PurchaseItem;
import com.jayghz.bookhub.model.entity.User;

@Component
public class PurchaseMapper {
    private final ModelMapper modelMapper;

    public PurchaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    // Convertir PurchaseCreateDTO a Purchase (Crear una compra)
    public Purchase toPurchaseCreateDTO(PurchaseCreateDTO purchaseDTO) {
        Purchase purchase = modelMapper.map(purchaseDTO, Purchase.class);

        // Mapear manualmente el cliente
        User customer = new User();
        customer.setId(purchaseDTO.getUserId());
        purchase.setCustomer(customer);

        // Mapear manualmente los items de la compra
        purchase.setItems(purchaseDTO.getItems().stream()
                .map(this::toPurchaseItemEntity)
                .toList());

        return purchase;
    }

    // Convertir Purchase a PurchaseDTO (Mostrar una compra)
    public PurchaseDTO toPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = modelMapper.map(purchase, PurchaseDTO.class);

        // Mapear manualmente el nombre del cliente
        purchaseDTO.setCustomerName(purchase.getCustomer().getFirstName() + " " + purchase.getCustomer().getLastName());

        // Mapear manualmente los items de la compra
        purchaseDTO.setItems(purchase.getItems().stream()
                .map(this::toPurchaseItemDTO)
                .toList());
        return purchaseDTO;
    }

    // Convertir PurchaseItemCreateDTO a PurchaseItem
    private PurchaseItem toPurchaseItemEntity(PurchaseItemCreateDTO itemDTO) {
        PurchaseItem item = modelMapper.map(itemDTO, PurchaseItem.class);
        Book book = new Book();
        book.setId(itemDTO.getBookId());
        item.setBook(book);
        return item;
    }

    // Convertir PurchaseItem a PurchaseItemDTO
    public PurchaseItemDTO toPurchaseItemDTO(PurchaseItem item) {
        PurchaseItemDTO itemDTO = modelMapper.map(item, PurchaseItemDTO.class);
        itemDTO.setBookTitle(item.getBook().getTitle());
        return itemDTO;
    }
}
