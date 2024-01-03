package com.saeinlee.dblab.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.saeinlee.dblab.domain.ProductInfo;
import com.saeinlee.dblab.domain.ProductStock;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class ProductStockRepositoryTest {
	
	@Autowired
	private ProductStockRepository productStockRepository;
	
	@Autowired
	private ProductInfoRepository productInfoRepository;


   @BeforeEach
   void setUp() {
       ProductInfo result = productInfoRepository.saveAndFlush(
       		ProductInfo.builder()
       		.id(1)
       		.name("water")
       		.build());
       
       productStockRepository.saveAndFlush(
       		ProductStock.builder()
       		.id(1)
       		.productId(result.getId())
       		.quantity(4000)
       		.build());
   }

   @AfterEach
   void tearDown() {
   	productStockRepository.deleteAllInBatch();
   	productInfoRepository.deleteAllInBatch();
   }

	

//	@Test
//	@DisplayName(value = "상품 재고 사용 ")
//	void test1() {
//		//given
//		List<ProductStock> productStock = productStockRepository.findAll();
//		
//		//when
//		productStock.get(0).decrease(1);
//		System.out.println(Thread.currentThread());
//		
//		
//		//then
//       assertThat(productStock.get(0).getQuantity()).isEqualTo(99);
//
//	}
   
   public synchronized void method() {
	   
   }
	
	@Test
	@DisplayName(value = "상품 재고 사용 다중 스레드 ")
	void test2() throws InterruptedException {
		//given
		List<ProductStock> productStock = productStockRepository.findAll();
		int numberOfThread = 4000;
		
		//when
		// 스레드를 관리하는 쓰레드 풀 객체 생
		ExecutorService service = Executors.newFixedThreadPool(numberOfThread);
			
		for(int i = 0; i < numberOfThread; i++) {
			service.submit(() -> {
				
				productStock.get(0).decrease(1);	
				log.info("### {}, :: {}", Thread.currentThread(), productStock.get(0).getQuantity());

			});
		}

		log.info("&1 {}", productStock.get(0).getQuantity());
		//쓰레드 모두 수행 후 종
		service.shutdown();
		log.info("&2 {}", productStock.get(0).getQuantity());

		//then
       assertThat(productStock.get(0).getQuantity()).isEqualTo(0);

	}


}
