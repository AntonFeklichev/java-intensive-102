package antonfeklichev.java_intensiv_102.service;

import antonfeklichev.java_intensiv_102.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void methodThatThrowsException() throws CustomException {
        throw new CustomException("Возникла ошибка в методе methodThatThrowsException!");
    }

    public void methodThatCatchesException() {
        try {
            methodThatThrowsException();
        } catch (CustomException e) {
            System.out.println("Перехвачено исключение: " + e.getClass().getSimpleName()
                               + ". Сообщение: " + e.getMessage());
        }
    }
}
