package com.wearsafe.web.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.wearsafe.dao.entity.AddressEntity;
import com.wearsafe.dao.entity.PersonEntity;
import com.wearsafe.helper.Utils;
import com.wearsafe.web.controller.vo.AddressVO;
import com.wearsafe.web.controller.vo.PersonVO;

/**
 * 
 * @author nagendra
 *
 */
public class PersonMapper {

	public static PersonVO toVO(PersonEntity entity) {
			PersonVO personVO=new PersonVO();
			BeanUtils.copyProperties(entity, personVO);
			List<AddressEntity> addressList=entity.getAddresses();
			List<AddressVO> addressVOList=new ArrayList<AddressVO>();
			if(addressList!=null) {
				addressList.forEach(s->{
					AddressVO addressVO=new AddressVO();
					BeanUtils.copyProperties(s, addressVO);
					addressVOList.add(addressVO);
				});
				personVO.setAddresses(addressVOList);
			}
			return personVO;
	}
	
	public static PersonEntity toEntity(PersonVO personVO,Utils.MODE mode) {
		PersonEntity entity=new PersonEntity();
		if(mode==Utils.MODE.CREATE) {
			BeanUtils.copyProperties(personVO, entity);
			entity.setDoe(Utils.currentDate());
			entity.setDom(Utils.currentDate());
		}
		List<AddressVO> addressVOs=personVO.getAddresses();
		List<AddressEntity> addressEntityList=new ArrayList<AddressEntity>();
		if(addressVOs!=null) {
			addressVOs.forEach(s->{
				if(s.getStreet()!=null) {
					AddressEntity addressEntity=new AddressEntity();
					BeanUtils.copyProperties(s, addressEntity);
					addressEntity.setDoe(Utils.currentDate());
					addressEntity.setDom(Utils.currentDate());
					addressEntityList.add(addressEntity);
				}
			});
			entity.setAddresses(addressEntityList);
		}
		return entity;
	}
	
	

}
