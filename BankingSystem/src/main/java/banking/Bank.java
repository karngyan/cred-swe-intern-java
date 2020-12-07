package banking;

import java.util.LinkedHashMap;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts;

	public Bank() {
		this.accounts = new LinkedHashMap<Long, Account>();
	}

	private Account getAccount(Long accountNumber) {
        return this.accounts.get(accountNumber);
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		Account account = new CommercialAccount(company, (long) this.accounts.size(), pin, startingDeposit);
		this.accounts.put(account.getAccountNumber(), account);
        return account.getAccountNumber();
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		Account account = new ConsumerAccount(person, (long) this.accounts.size(), pin, startingDeposit);
		this.accounts.put(account.getAccountNumber(), account);
        return account.getAccountNumber();
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
        return this.accounts.get(accountNumber).validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
        return this.accounts.get(accountNumber).getBalance();
	}

	public void credit(Long accountNumber, double amount) {
		// complete the function
		Account account = this.accounts.get(accountNumber);
		account.creditAccount(amount);
		this.accounts.put(accountNumber, account);
	}

	public boolean debit(Long accountNumber, double amount) {
		// complete the function
		Account account = this.accounts.get(accountNumber);
		if (account.debitAccount(amount)) {
			this.accounts.put(accountNumber, account);
			return true;
		}
        return false;
	}
}
