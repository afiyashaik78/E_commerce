package com.ecom.service;


import com.ecom.model.Seller;
import com.ecom.model.User;
import com.ecom.model.UserType;
import com.ecom.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    
    @Autowired
    private UserService userService;
    
    public Seller registerSeller(Seller seller) {
        userService.registerUser(seller, UserType.SELLER);
        return sellerRepository.save(seller);
    }
    
    public Seller updateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }
    
    public Seller findByEmail(String email) {
        User user = userService.findByEmail(email);
        if (user != null && user.getUserType().equals(UserType.SELLER)) {
            return sellerRepository.findById(user.getId()).orElse(null);
        }
        return null;
    }
}