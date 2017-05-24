package com.goit.startup.service;

import com.goit.startup.entity.Investment;
import com.goit.startup.repository.DataRepository;
import com.goit.startup.repository.InvestmentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Class for testing {@link InvestmentServiceImpl}.
 *
 * @author Slava Makhinich
 * Created on 24.05.2017.
 * @version 1.0
 */
public class InvestmentServiceImplTest extends DataServiceImplTest<Investment> {

    /**
     * An instance of {@link Investment}
     */
    private Investment investment;

    /**
     * An instance of implementation {@link InvestmentRepository}.
     */
    private InvestmentRepository repository;

    /**
     * An instance of {@link InvestmentServiceImpl}
     */
    private InvestmentServiceImpl service;

    public InvestmentServiceImplTest() {
        this.investment = new Investment();
        investment.setId(0);
        investment.setAmount(0);
        this.repository = mock(InvestmentRepository.class);
        this.service = new InvestmentServiceImpl(repository);
    }

    /**
     * Getter for field service.
     *
     * @return field service.
     */
    @Override
    protected DataService<Investment> getService() {
        return this.service;
    }

    /**
     * Getter for field investment.
     *
     * @return field investment.
     */
    @Override
    protected Investment getObject() {
        return this.investment;
    }

    /**
     * Getter for list of investments.
     *
     * @return list of investments.
     */
    @Override
    protected List<Investment> getObjects() {
        List<Investment> investments = new ArrayList<>();
        investments.add(investment);
        Investment investment1 = new Investment();
        investment1.setId(1);
        investment1.setAmount(1);
        investments.add(investment1);
        return investments;
    }

    /**
     * Getter for field repository.
     *
     * @return field repository.
     */
    @Override
    protected DataRepository<Investment> getRepository() {
        return this.repository;
    }
}