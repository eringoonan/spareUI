package com.example.spareui.readers;

import com.example.spareui.accommodations.Address;

import java.util.List;
import java.util.Map;

public class AddressData { // object used to return both addresslist and addressmap, maps address ID to property ID
    private List<Address> addressList;
    private Map<Integer, Address> addressMap;

    public AddressData(List<Address> addressList, Map<Integer, Address> addressMap) {
        this.addressList = addressList;
        this.addressMap = addressMap;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public Map<Integer, Address> getAddressMap() {
        return addressMap;
    }
}
