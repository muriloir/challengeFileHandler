package br.com.challengefilehandler.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sale {
    String saleId;
    List<SaleItems> salesItems;
    String salesmanName;
}