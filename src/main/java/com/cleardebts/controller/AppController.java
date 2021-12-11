package com.cleardebts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cleardebts.exception.ClearDebtsException;
import com.cleardebts.exception.RecordNotFoundException;
import com.cleardebts.frontend.input.ItemWeight;
import com.cleardebts.frontend.input.OrderInput;
import com.cleardebts.model.ChemicalFormula;
import com.cleardebts.model.Order;
import com.cleardebts.model.RawItem;
import com.cleardebts.service.ChemicalAppService;

@Controller
@RequestMapping("/app")
public class AppController {

	@Autowired
	private ChemicalAppService appService;

	@PostMapping("/item/")
	public ResponseEntity<RawItem> createNewItem(@RequestBody RawItem item) throws Exception {

		try {
			item = appService.createItem(item);

		} catch (ClearDebtsException cde) {
		}
		return ResponseEntity.ok(item);
	}

	@PutMapping("/item/")
	public ResponseEntity<RawItem> updateItem(@RequestBody RawItem item) throws Exception {

		item = appService.updateItem(item);

		return ResponseEntity.ok(item);
	}

	@GetMapping("/item/getItemById/{id}")
	public ResponseEntity<RawItem> getItemById(@PathVariable Long id) throws RecordNotFoundException {

		return ResponseEntity.ok(appService.getItemBy(id));
	}

	@GetMapping("/item/getAllItems")
	public ResponseEntity<List<RawItem>> getUser() throws RecordNotFoundException {

		return ResponseEntity.ok(appService.getAllRawItems());
	}

	// Formula
	@PostMapping("/formula/")
	public ResponseEntity<ChemicalFormula> createNewFormula(@RequestBody ChemicalFormula chemicalFormula)
			throws Exception {

		try {
			chemicalFormula = appService.createFormula(chemicalFormula);

		} catch (ClearDebtsException cde) {
		}
		return ResponseEntity.ok(chemicalFormula);
	}
	
	@PutMapping("/formula/")
	public ResponseEntity<ChemicalFormula> updateFormula(@RequestBody ChemicalFormula chemicalFormula) throws Exception {

		chemicalFormula = appService.updateFormula(chemicalFormula);

		return ResponseEntity.ok(chemicalFormula);
	}

	@GetMapping("/formula/getAllFormulas")
	public ResponseEntity<List<ChemicalFormula>> getAllFormulas() throws RecordNotFoundException {

		return ResponseEntity.ok(appService.getAllFormulas());
	}

	@GetMapping("/formula/getFormulaById/{id}")
	public ResponseEntity<ChemicalFormula> getFormulaById(@PathVariable Long id) throws RecordNotFoundException {

		return ResponseEntity.ok(appService.getFormulaBy(id));
	}

	@GetMapping("/formula/populateItemCalculationForFormula/{id}/{weight}")
	public ResponseEntity<List<ItemWeight>> populateItemCalculationForFormula(@PathVariable Long id,
			@PathVariable Long weight) throws RecordNotFoundException {

		return ResponseEntity.ok(appService.populateItemCalculationForFormula(id, weight));
	}

	@PostMapping("/order/")
	public ResponseEntity<OrderInput> createNewOrder(@RequestBody OrderInput orderInput) throws Exception {

		try {
			orderInput = appService.createOrder(orderInput);

		} catch (ClearDebtsException cde) {
		}
		return ResponseEntity.ok(orderInput);
	}

	@GetMapping("/order/getOrderById/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws RecordNotFoundException {

		return ResponseEntity.ok(appService.getOrderBy(id));
	}

	@GetMapping("/order/getAllOrders")
	public ResponseEntity<List<Order>> getOrders() throws RecordNotFoundException {

		return ResponseEntity.ok(appService.getAllOrders());
	}

	@DeleteMapping("/order/{id}")
	public void deleteOrderById(@PathVariable Long id) throws RecordNotFoundException {
		appService.deleteOrderById(id);
	}
}
