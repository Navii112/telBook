package service;

import dto.TelDto;
import repository.TelBookRepository;

import java.util.List;

public class TelBookService {
    private final TelBookRepository repository;

    public TelBookService(TelBookRepository repository) {
        this.repository = repository;
    }
    public void insert(String name, int age, String address, String phone) {
        // 받은 자료로 TelDto를 생성
        TelDto dto = new TelDto(0L, name, age, address, phone);
        // repo 호출
        int result = repository.insertData(dto);
        if (result > 0) {
            System.out.println("정상적으로 저장되었습니다.");
        }
    }

    public List<TelDto> getListAll() {
        return repository.findAll();
    }

    public List<TelDto> getListOne() {
        return repository.findById(id);
    }

    public int delete(int id) {
        return repository.deleteById(id);
    }
}
