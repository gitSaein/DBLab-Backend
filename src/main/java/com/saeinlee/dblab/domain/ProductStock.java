package com.saeinlee.dblab.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_stock")
public class ProductStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "product_id")
	private int productId;
	@Column
	private int quantity;
	
    public void decrease(int quantity) {
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.quantity = this.quantity - quantity;
    }

}
