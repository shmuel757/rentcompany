package telran.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.cars.dto.*;
import telran.cars.model.IRentCompany;

import static telran.cars.api.ApiConstants.*;

import java.util.List;
@RestController
public class RentCompanyController {
	@Autowired
	IRentCompany company;
@PostMapping(value=ADD_MODEL)
CarsReturnCode addModel(@RequestBody Model model) {
	return company.addModel(model);
}
@PostMapping(value=ADD_CAR)
CarsReturnCode addCar(@RequestBody Car car) {
	return company.addCar(car);
}
@GetMapping(value=GET_CAR_MODELS)
List<String> getModelNames() {
	return company.getModelNames();
}
@GetMapping(value=GET_CAR)
Car getCar(@RequestParam(name=CAR_NUMBER)
String regNumber) {
	return company.getCar(regNumber);
	
}

}
