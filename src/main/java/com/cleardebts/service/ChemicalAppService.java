package com.cleardebts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleardebts.exception.ClearDebtsException;
import com.cleardebts.exception.RecordNotFoundException;
import com.cleardebts.frontend.input.ItemWeight;
import com.cleardebts.frontend.input.OrderInput;
import com.cleardebts.model.ChemicalFormula;
import com.cleardebts.model.Order;
import com.cleardebts.model.RawItem;
import com.cleardebts.repository.ChemicalFormulaRepo;
import com.cleardebts.repository.OrderRepo;
import com.cleardebts.repository.RawItemRepo;

@Service
public class ChemicalAppService {

	@Autowired
	private RawItemRepo itemRepo;

	@Autowired
	private ChemicalFormulaRepo chemicalFormulaRepo;

	@Autowired
	private OrderRepo orderRepo;

	public RawItem createItem(RawItem rawItem) throws ClearDebtsException {

		try {
			rawItem = itemRepo.save(rawItem);

			System.out.println("Item saved successfully");

			// TODO Handle excepion for all the cases
		} catch (Exception exception) {
			System.out.println("Item fail to save");
		}

		return rawItem;
	}

	public RawItem getItemBy(Long id) throws RecordNotFoundException {

		Optional<RawItem> rawItem = itemRepo.findById(id);

		if (!rawItem.isPresent()) {
			throw new RecordNotFoundException("Item not found exception");
		}

		return rawItem.get();
	}

	public List<RawItem> getAllRawItems() throws RecordNotFoundException {
		return itemRepo.findAll();
	}

	// Forumula
	public ChemicalFormula createFormula(ChemicalFormula chemicalFormula) throws ClearDebtsException {

		try {

			// Standard calculation as per 100 gm - 1-10||2-20||3-30

//			String itemWeightDetai =  chemicalFormula.getItemsDet();
//			
//			String itemWeightDetSplit [] = itemWeightDetai.split("\\|");
//			
//			for(String itemWeightString : itemWeightDetSplit) {
//				String itemWeightSplit[] = itemWeightString.split("-");
//				
//				ItemWeight itemWeight = new ItemWeight();
//				itemWeight.setId(Long.valueOf(itemWeightSplit[0]));
//				itemWeight.setWeight(Long.valueOf(itemWeightSplit[1]));
//				
//				
//			}

			chemicalFormula = chemicalFormulaRepo.save(chemicalFormula);

			System.out.println("Chemical formulae saved successfully");

			// TODO Handle excepion for all the cases
		} catch (Exception exception) {
			System.out.println("Item fail to save");
		}

		return chemicalFormula;
	}

	public ChemicalFormula getFormulaBy(Long id) throws RecordNotFoundException {

		Optional<ChemicalFormula> chemicalFormula = chemicalFormulaRepo.findById(id);

		if (!chemicalFormula.isPresent()) {
			throw new RecordNotFoundException("Forumula not found exception");
		}

		return chemicalFormula.get();
	}

	public List<ItemWeight> populateItemCalculationForFormula(Long formulaId, Long weight)
			throws RecordNotFoundException {

		ChemicalFormula chemicalFormula = getFormulaBy(formulaId);

		Long proportion = weight / 100;

		List<ItemWeight> itemWeightList = new ArrayList<ItemWeight>();

		String itemWeightDetai = chemicalFormula.getItemsDet();

		String itemWeightDetSplit[] = itemWeightDetai.split("\\|");

		for (String itemWeightString : itemWeightDetSplit) {
			String itemWeightSplit[] = itemWeightString.split("-");

			ItemWeight itemWeight = new ItemWeight();
			itemWeight.setId(Long.valueOf(itemWeightSplit[0]));
			RawItem rawItem = getItemBy(itemWeight.getId());
			itemWeight.setWeight(Long.valueOf(itemWeightSplit[1]) * proportion);
			itemWeight.setDescr(rawItem.getName());
			itemWeightList.add(itemWeight);
		}

		return itemWeightList;
	}

	public List<ChemicalFormula> getAllFormulas() throws RecordNotFoundException {
		return chemicalFormulaRepo.findAll();
	}

	// Orders
	@Transactional
	public OrderInput createOrder(OrderInput orderInput) throws Exception {

		try {

			ChemicalFormula chemicalFormula = getFormulaBy(orderInput.getFormulaId());
			List<ItemWeight> itemWeightList = populateItemCalculationForFormula(chemicalFormula.getId(),
					orderInput.getQty());

			for (ItemWeight itemWeight : itemWeightList) {
				RawItem item = getItemBy(itemWeight.getId());
				item.setStock(item.getStock() - itemWeight.getWeight());
				if (item.getStock() < 0) {
					String errorMsg = "Item " + item.getName() + " stock equires" + itemWeight.getWeight() + "but have"
							+ item.getStock();
					throw new RuntimeException(errorMsg);
				}
				itemRepo.save(item);
			}

			Order order = new Order();
			order.setDescr(orderInput.getDescr());
			order.setFormula(getFormulaBy(orderInput.getFormulaId()));
			order.setQty(orderInput.getQty());

			order = orderRepo.save(order);

			orderInput.setId(order.getId());

			System.out.println("Order saved successfully");

			// TODO Handle excepion for all the cases
		} catch (Exception exception) {
			System.out.println("Order fail to save" + exception);
			throw new Exception(exception.getMessage());
		}

		return orderInput;
	}

	public Order getOrderBy(Long id) throws RecordNotFoundException {

		Optional<Order> order = orderRepo.findById(id);

		if (!order.isPresent()) {
			throw new RecordNotFoundException("Order not found exception");
		}

		return order.get();
	}

	public List<Order> getAllOrders() throws RecordNotFoundException {
		return orderRepo.findAll();
	}

	public void deleteOrderById(Long id) throws RecordNotFoundException {

		Order order = getOrderBy(id);

		ChemicalFormula chemicalFormula = order.getFormula();

		List<ItemWeight> itemWeightList = populateItemCalculationForFormula(chemicalFormula.getId(), order.getQty());

		for (ItemWeight itemWeight : itemWeightList) {
			RawItem item = getItemBy(itemWeight.getId());
			item.setStock(item.getStock() + itemWeight.getWeight());
			itemRepo.save(item);
		}

		orderRepo.deleteById(id);
	}
}
