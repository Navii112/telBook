package repository;

import db.DBConn;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelBookRepository {
    // 1. DB 연결
    private final Connection conn;

    public TelBookRepository(Connection conn) {
        this.conn = DBConn.getConnection();
    }

    public int insertData(TelDto dto) {


        PreparedStatement psmt = null;
        // 2. 쿼리 생성
        // 실행 결과를 담을 변수
        int result = 0;
        try {
            String sql = "INSERT INTO telbook (name, age, address, phone) VALUES (?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getTelNumber());
            result = psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println("INSERT 오류 : " + e.getMessage());
        }
        return result;
    }

    public List<TelDto> findAll() {
        List<TelDto> dtoList = new ArrayList<>();
        // 쿼리를 실행 할 도구
        PreparedStatement psmt = null;
        // 검색 결과 레코드 셋을 담을 통
        ResultSet rs = null;
        try {
            // 쿼리 작성
            String sql = "SELECT * FROM telbook ORDER BY name";
            psmt = conn.prepareStatement(sql);
            // 싱행 -> 결과는 rs가 받는다.
            rs = psmt.executeQuery();
            // 받은 결과를 DTO List에 차곡차곡 담는다.
            // rs.next() : 다음 레코드가 있나?
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));
//                System.out.println(dto);
                // 만들어진 dto를 List에 담는다.
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Find All Error : " + e.getMessage());
        }
        return dtoList;
    }

    public List<TelDto> findById(int id) {
        List<TelDto> dtoList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM telbook WHERE id = ?";
            psmt = conn.prepareStatement(sql);

            // [수정] psmt.setId("id"); -> setInt()를 사용하고 매개변수 id를 전달
            psmt.setInt(1, id);

            rs = psmt.executeQuery();

            // 리스트에 추가
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));

                // [수정] 생성한 dto를 리스트에 추가하는 코드 작성
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("FindById Error : " + e.getMessage());
        }

        // [수정] 최종 결과 리스트를 리턴
        return dtoList;
    }

    public int deleteById(int id) {
        PreparedStatement psmt = null;
        // 2. 쿼리 생성
        // 실행 결과를 담을 변수
        int result = 0;
        try {
            String sql = "DELETE FROM telbook WHERE id = ?)";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            result = psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println("DELETE 오류 : " + e.getMessage());
        }
        return result;

    }

    public void update(TelDto updateData) {
        PreparedStatement psmt = null;
        // 2. 쿼리 생성

        // 실행 결과를 담을 변수
        int result = 0;
        try {
            String sql = "UPDATE telbook ";
            sql = sql + "SET name =?, ";
            sql = sql + "age = ?, ";
            sql = sql + "address =?, ";
            sql = sql + "phone=? ";
            sql = sql + "WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, updateData.getName());
            psmt.setInt(2, updateData.getAge());
            psmt.setString(3, updateData.getAddress());
            psmt.setString(4, updateData.getTelNumber());
            psmt.setLong(5,updateData.getId());
            psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println("INSERT 오류 : " + e.getMessage());
        }
    }

    public List<TelDto> search(int choice, String keyword) {
        List<TelDto> dtoList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(choice == 1){
                // 이름 검색
                sql = "SELECT * FROM telbook WHERE name LIKE ?";
            } else {
                // 주소 검색
                sql = "SELECT * FROM telbook WHERE address LIKE ?";
            }

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + keyword + "%");
            rs = psmt.executeQuery();
            // 리스트에 추가
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Search Error : " + e.getMessage());
        }
        return dtoList;
    }
}
