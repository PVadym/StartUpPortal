package com.goit.startup.service;

import com.goit.startup.entity.Image;
import com.goit.startup.repository.DataRepository;
import com.goit.startup.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Class for testing {@link ImageServiceImpl}.
 *
 * @author Slava Makhinich
 * Created on 24.05.2017.
 * @version 1.0
 */
public class ImageServiceImplTest extends DataServiceImplTest<Image>{

    /**
     * An instance of {@link ImageServiceImpl}
     */
    private ImageServiceImpl service;

    /**
     * An instance of {@link Image}
     */
    private Image image;

    /**
     * An instance of implementation {@link ImageRepository}.
     */
    private ImageRepository repository;

    /**
     * Constructor
     */
    public ImageServiceImplTest() {
        this.image = new Image();
        image.setId(0);
        image.setData(new byte[]{1, 2, 3});
        this.repository = mock(ImageRepository.class);
        this.service = new ImageServiceImpl(repository);
    }

    /**
     * Getter for field service.
     *
     * @return field service.
     */
    @Override
    protected DataService<Image> getService() {
        return this.service;
    }

    /**
     * Getter for field image.
     *
     * @return field image.
     */
    @Override
    protected Image getObject() {
        return this.image;
    }

    /**
     * Getter for list of images.
     *
     * @return list of images.
     */
    @Override
    protected List<Image> getObjects() {
        List<Image> images = new ArrayList<>();
        images.add(image);
        Image image1 = new Image();
        image1.setId(1);
        image1.setData(new byte[]{4, 5, 6, 7});
        images.add(image1);
        return images;
    }

    /**
     * Getter for field repository.
     *
     * @return field repository.
     */
    @Override
    protected DataRepository<Image> getRepository() {
        return this.repository;
    }
}