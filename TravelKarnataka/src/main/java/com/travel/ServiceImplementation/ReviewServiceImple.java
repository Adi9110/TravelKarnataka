package com.travel.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.ReviewEntity;
import com.travel.repository.ReviewRepository;
import com.travel.service.ReviewService;

@Service
public class ReviewServiceImple implements ReviewService{
	 @Autowired
	    private ReviewRepository reviewRepository;
	@Override
	public int saveReview(ReviewEntity review) {
		return reviewRepository.save(review).getRId();
	}

}
