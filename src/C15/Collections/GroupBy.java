package C15.Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// MyObject class with constructor, getters and toString method
class MyObject {
    public String id;
    public String name;
    public String avatarId;
    public List<Address> address = new ArrayList<>();
    public String countryCode;
    public String currency;

    record KeyGroup(String addressId, String branchId) {};

    public MyObject(String id, String name, String avatarId, Address address, String countryCode, String currency) {
        this.id = id;
        this.name = name;
        this.avatarId = avatarId;
        this.address.add(address);
        this.countryCode = countryCode;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatarId='" + avatarId + '\'' +
                ", address=" + address +
                ", countryCode='" + countryCode + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}

// Address class with constructor and toString method
class Address {
    public String id;
    public String country;
    public String province;
    public String district;
    public String ward;

    public Address(String id, String country, String province, String district, String ward) {
        this.id = id;
        this.country = country;
        this.province = province;
        this.district = district;
        this.ward = ward;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", ward='" + ward + '\'' +
                '}';
    }
}

public class GroupBy {

    public static void main(String[] args) {
        // Creating a list of objects
        List<MyObject> objects = new ArrayList<>();

        // Adding data to the list
        objects.add(new MyObject("3d634c5b-9bfa-4f35-90a3-13f7b3e73d30", "Ngôi nhà hạnh phúc", "", new Address("0f9c8b63-812c-47c5-9871-93cc6f0c9174", "Việt Nam", "Hà Nội", "Quận Cầu Giấy", "Phường Dịch Vọng"), "VN", "VND"));
        objects.add(new MyObject("a2357b1d-7b57-4a2a-8e5b-55d6d4e9a5e0", "Mua vui bán buồn", "", new Address("e91a69b9-6a74-4b5d-89d1-87e1d59cc1d0", "Việt Nam", "Đà Nẵng", "Quận Hải Châu", "Phường Thạch Thang"), "VN", "VND"));
        objects.add(new MyObject("1c4bfb6d-3f1c-4c3c-bbfa-b8c3cb9fc6bb", "Tình yêu không tuổi", "", new Address("e78d57a2-9233-4a39-82a9-2261f25ad7d6", "Việt Nam", "Hải Phòng", "Quận Ngô Quyền", "Phường Cầu Đất"), "VN", "VND"));
        objects.add(new MyObject("f930c568-fd6d-4277-8c10-c0548e990850", "Chợ tình buồn", "", new Address("c7b7287a-0155-438d-9008-98cc57921455", "Việt Nam", "Bình Dương", "Thành phố Thủ Dầu Một", "Phường Phú Cường"), "VN", "VND"));
        objects.add(new MyObject("dbed9ab7-7fa7-4d5e-bc4d-dcb8285d2bff", "Tình yêu qua thời gian", "", new Address("5fc67db8-2bb3-4028-9ff4-cb0c9f4a82d4", "Việt Nam", "Cần Thơ", "Quận Ninh Kiều", "Phường An Cư"), "VN", "VND"));
        objects.add(new MyObject("dd341b8f-7f53-4e33-b939-26ee9f64300d", "Chuyện tình xa xôi", "", new Address("56305c7e-7b7d-42a5-81fa-d6a0c029072d", "Việt Nam", "Quảng Ninh", "Thành phố Hạ Long", "Phường Bãi Cháy"), "VN", "VND"));
        objects.add(new MyObject("f7d9c6b4-9d69-4ed6-a14f-f6e693f6a88b", "Tiệm cà phê yêu thương", "", new Address("1f9d92bb-b7fc-42a3-bd89-c1cdca9c9f2a", "Việt Nam", "Bắc Ninh", "Thành phố Bắc Ninh", "Phường Vũ Ninh"), "VN", "VND"));
        objects.add(new MyObject("c27f8ef4-1be4-40c0-a98a-f5b405ebf205", "Quán nhỏ buồn vui", "", new Address("d238bdb2-9f1d-4427-8e72-cd0cbce2f070", "Việt Nam", "Hà Giang", "Thành phố Hà Giang", "Phường Minh Khai"), "VN", "VND"));
        objects.add(new MyObject("a23355a7-92cd-4d52-96f5-e1976d74f745", "Những giấc mơ xa vời", "", new Address("7e609f8d-9e2a-472e-897a-bb71211b4f7c", "Việt Nam", "Lâm Đồng", "Thành phố Đà Lạt", "Phường 9"), "VN", "VND"));
        objects.add(new MyObject("a23355a7-92cd-4d52-96f5-e1976d74f745", "Những giấc mơ xa vời", "", new Address("031f003b-83a7-4a9b-8813-b7ba047c72e7", "Việt Nam", "Tp Hồ Chí Minh", "Quận 1", "Phường Đa Kao"), "VN", "VND"));
        objects.add(new MyObject("a23355a7-92cd-4d52-96f5-e1976d74f745", "Những giấc mơ xa vời", "", new Address("031f003b-83a7-4a9b-8813-b7ba047c72e7", "Việt Nam", "Tp Hồ Chí Minh", "Quận 1", "Phường Đa Kao"), "VN", "VND"));

        // Output the list (for demonstration)
//        for (MyObject obj : objects) {
//            System.out.println(obj);
//        }

        Map<MyObject.KeyGroup, List<MyObject>> res =
                objects.stream().collect(Collectors.groupingBy(e -> new MyObject.KeyGroup(e.id, e.address.get(0).id)));

        for (MyObject.KeyGroup key : res.keySet()) {
            System.out.println(res.get(key));
        }

    }


}

