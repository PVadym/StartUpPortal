package com.goit.startup.service;

import com.goit.startup.entity.Image;
import com.goit.startup.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link ImageService}
 *
 * @author Perevoznyk Pavlo
 *         created on 17 may 2017
 * @version 1.0
 */
@Service
public class ImageServiceImpl extends DataServiceImpl<Image> implements ImageService {

    private ImageRepository repository;

    @Autowired
    public ImageServiceImpl(ImageRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
