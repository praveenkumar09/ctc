package com.censof.myfi.hidefmyfi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.repository.HiCountryRepository;
import com.censof.myfi.hidefmyfi.service.HiCountryService;
@Service("hiCountryService")
public class HiCountryServiceImpl implements HiCountryService{

	@Autowired
	private HiCountryRepository hiCountryRepository;
	
	
	@Override
	public List<Hicountry> getAllCountries() {
		// TODO Auto-generated method stub
		return hiCountryRepository.findAllCountry();
	}

}
