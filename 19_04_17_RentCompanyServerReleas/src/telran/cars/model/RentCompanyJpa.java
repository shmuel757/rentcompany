package telran.cars.model;
import telran.cars.entities.*;
import telran.cars.repo.*;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.cars.entities.*;
import telran.cars.dto.*;

@Service
public class RentCompanyJpa extends AbstractRentCompany {
	@Autowired
	CarRepository carRepository;
	@Autowired
	RecordRepository recordRepository;
	@Autowired
	DriverRepositry driverRepositry;
	@Autowired
	ModelRepository modelRepository;

	@Override
	public CarsReturnCode addModel(Model model) {
		if (modelRepository.existsById(model.getModelName()))
			return CarsReturnCode.MODEL_EXISTS;
		ModelJpa modelJpa = new ModelJpa(model.getModelName(),
				model.getGasTank(), model.getCompany(), model.getCountry(),
				model.getPriceDay());
		modelRepository.save(modelJpa);
		return CarsReturnCode.OK;
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		String regNumber = car.getRegNumber();
		if (carRepository.existsById(regNumber))
			return CarsReturnCode.CAR_EXISTS;
		String modelName = car.getModelName();
		ModelJpa modelJpa = modelRepository.findById(modelName).orElse(null);
		if (modelJpa == null) {
			return CarsReturnCode.NO_MODEL;
		}
		CarJpa carJpa = new CarJpa();
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model getModel(String modelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car getCar(String regNumber) {
		CarJpa carJpa = carRepository.findById(regNumber)
				.orElse(null);
		return carJpa==null? null: getCarDto(CarJpa carJpa){
			
		}
	}

	private Object getCarDto(CarJpa carJpa) {
Car res = new Car(carJpa.getRegNumber(), carJpa.getColor(), modelName)		return null;
	}

	@Override
	public Driver getDriver(long licenseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public CarsReturnCode rentCar(String regNumber, long licenseId,
			LocalDate rentDate, int rentDays) {
		CarJpa carJpa = carRepository.findById(regNumber).orElse(null);
		if (carJpa == null)
			return CarsReturnCode.NO_CAR;
		DriverJpa driverJpa = driverRepositry.findById(licenseId).orElse(null);
		if (driverJpa == null)
			return CarsReturnCode.NO_DRIVER;
		RecordJpa record = recordRepository
				.findByCarRegNumberAndReturnDateNull(regNumber);
		if (record != null)
			return CarsReturnCode.CAR_IN_USE;
		RecordJpa recordJpa = new RecordJpa(rentDate, rentDays, carJpa,
				driverJpa);
		recordRepository.save(recordJpa);
		return CarsReturnCode.OK;

	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Driver> getCarDrivers(String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> getModelCars(String modelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecord> getRentRecordsAtDates(LocalDate from,
			LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemovedCarData removeCar(String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RemovedCarData> removeModel(String modelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemovedCarData returnCar(String regNumber, long licenseId,
			LocalDate returnDate, int damages, int tankPercent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMostPopularCarModels(LocalDate fromDate,
			LocalDate toDate, int fromAge, int toAge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMostProfitableCarModels(LocalDate fromDate,
			LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Driver> getMostActiveDrivers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transactional(readOnly=true)
	public List<String> getModelNames() {
		return modelRepository.;
	}

}
