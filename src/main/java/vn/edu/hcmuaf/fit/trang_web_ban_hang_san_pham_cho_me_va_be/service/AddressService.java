package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.AddressDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Address;

import java.util.List;

@RegisterBeanMapper(Address.class)
public class AddressService {
    AddressDao addressDao;


    public AddressService(Jdbi jdbi) {
        this.addressDao = jdbi.onDemand(AddressDao.class);
    }

    public List<Address> findByUserId(Integer user_id) {
        return addressDao.getAddressByUserId(user_id);
    }

    public Address findById(Integer id) {
        return addressDao.getAddressById(id);
    }


    public int addAddress(Address address) {
        // Đảm bảo isDefault mặc định là false nếu không được chỉ định
        if (address.getIs_default() == null) {
            address.setIs_default(false);//đại diện cho giá trị boolean
        }
        return addressDao.addAddress(
                address.getUser_id(),
                address.getAddress_type(),
                address.getFull_name(),
                address.getPhone_number(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getIs_default()
        );
    }



    public static void main(String[] args) {
        AddressService addressService = new AddressService(DBConnection.getJdbi());
//        Address address = new Address(null, 42, "HCM", 202, "ThuDuc"
//                , 203, "LinhTrung", 204, "detail"
//                ,"0299993222", "Name", false, "HOME", "ACTIVE"
//                );
//        addressSevice.addAddress(address);
        System.out.println(addressService.findByUserId(42));
    }





}

