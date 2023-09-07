package service;

import basics.service.BaseService;
import entity.Debt;
import entity.Loan;

import java.util.List;

public interface DebtService extends BaseService<Debt> {

    List<Debt> calculateDebts (Loan loan);
}
