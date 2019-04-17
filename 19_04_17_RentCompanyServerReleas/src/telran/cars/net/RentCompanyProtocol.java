package telran.cars.net;

import java.io.Serializable;
import java.lang.reflect.Method;

import telran.cars.dto.*;

import telran.cars.model.IRentCompany;
import telran.net.RequestJava;
import telran.net.ResponseJava;
import telran.net.TcpResponseCode;
import telran.net.server.ProtocolJava;

public class RentCompanyProtocol implements ProtocolJava{
IRentCompany company;


	public RentCompanyProtocol(IRentCompany company) {
	super();
	this.company = company;
}


	@Override
	public ResponseJava getResponse(RequestJava request) {
		try {
			Method method=this.getClass()
					.getDeclaredMethod(
				request.requestType.replaceAll
				("/", "_"), RequestJava.class);
			return (ResponseJava) method.invoke(this, request);
		} catch (Exception e) {
			return wrongRequest("uknown request "+request.requestType);
		}
		
	}
	public ResponseJava _model(RequestJava request) {
		try {
			String modelName=(String) request.requestData;
			Serializable responseData=
					(Serializable) company.getModel(modelName);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _driver_add(RequestJava request) {
		try {
			Driver driver =(Driver) request.requestData;
			Serializable responseData=
					(Serializable) company.addDriver(driver);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _driver_cars(RequestJava request) {
		try {
			Long licenseId=(Long) request.requestData;
			Serializable responseData=
					(Serializable) company.getDriverCars(licenseId);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _model_cars(RequestJava request) {
		try {
			String modelName=(String) request.requestData;
			Serializable responseData=
					(Serializable) company.getModelCars(modelName);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _car_rent(RequestJava request) {
		try {
			RentCarData data=(RentCarData) request.requestData;
			Serializable responseData=
					(Serializable) company.rentCar
					(data.getRegNumber(), data.getLicenseId(),
							data.getRentDate(), data.getRentDays());
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _drivers_car(RequestJava request) {
		try {
			String regNumber=(String) request.requestData;
			Serializable responseData=
					(Serializable) company.getCarDrivers(regNumber);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _driver(RequestJava request) {
		try {
			Long licenseId=(Long) request.requestData;
			Serializable responseData=
					(Serializable) company.getDriver(licenseId);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _car_add(RequestJava request) {
		try {
			Car car=(Car) request.requestData;
			Serializable responseData=
					(Serializable) company.addCar(car);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _drivers_active(RequestJava request) {
		try {
			
			Serializable responseData=
					(Serializable) company.getMostActiveDrivers();
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _car_return(RequestJava request) {
		try {
			ReturnCarData data=(ReturnCarData) request.requestData;
			
			Serializable responseData=
					(Serializable) company.returnCar
			(data.getRegNumber(), data.getLicenseId(),
				data.getReturnDate(), data.getDamages(), data.getTankPercent());
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _car(RequestJava request) {
		try {
			String carNumber=(String) request.requestData;
			Serializable responseData=
					(Serializable) company.getCar(carNumber);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _car_remove(RequestJava request) {
		try {
			String carNumber=(String) request.requestData;
			Serializable responseData=
					(Serializable) company.removeCar(carNumber);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _model_remove(RequestJava request) {
		try {
			String modelName=(String) request.requestData;
			Serializable responseData=
					(Serializable) company.removeModel(modelName);
			
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava _models_popular(RequestJava request) {
		try {
			StatisticsData data=(StatisticsData) request.requestData;
			Serializable responseData=
					(Serializable) company.getMostPopularCarModels
			(data.getFromDate(), data.getToDate(),
					data.getFromAge(), data.getToAge());
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
	public ResponseJava  _models_profitable(RequestJava request) {
		try {
			StatisticsData data=(StatisticsData) request.requestData;
			Serializable responseData=
					(Serializable) company.getMostProfitableCarModels
			(data.getFromDate(), data.getToDate());
			return new ResponseJava(TcpResponseCode.OK, responseData);
		} catch (Exception e) {
			return wrongRequest(e.getMessage());
		}
	}
public ResponseJava  _model_add(RequestJava request){
	try {
		Model model=(Model) request.requestData;
		Serializable responseData=company.addModel(model);
		return new ResponseJava(TcpResponseCode.OK, responseData);
	} catch (Exception e) {
		return wrongRequest(e.getMessage());
	}
	
}
public ResponseJava  _records(RequestJava request){
	try {
		StatisticsData data=(StatisticsData) request.requestData;
		Serializable responseData=(Serializable) company
				.getRentRecordsAtDates(data.getFromDate(), data.getToDate());
		return new ResponseJava(TcpResponseCode.OK, responseData);
	} catch (Exception e) {
		return wrongRequest(e.getMessage());
	}
	
}
private ResponseJava wrongRequest(String errorMessage) {
	
	return new ResponseJava
			(TcpResponseCode.WRONG_REQUEST, errorMessage);
}


public ResponseJava  _models(RequestJava request){
	try {
		Serializable responseData=(Serializable) company.getModelNames();
		return new ResponseJava(TcpResponseCode.OK, responseData);
	} catch (Exception e) {
		return wrongRequest(e.getMessage());
	}
	
}


}
