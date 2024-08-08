Let's consider a more complex scenario involving a system that manages different types of bank accounts. We'll use abstraction to define a common structure for all bank accounts while allowing for specific implementations in different account types.

Scenario: Bank Account Management System

Imagine a bank system where we have different types of bank accounts: SavingsAccount, CurrentAccount, and FixedDepositAccount. Each account type has a unique way of calculating interest and handling transactions, but they all share some common behaviors, such as depositing and withdrawing money.

Abstraction Example:

We'll create an abstract class, BankAccount, that provides the basic structure and behavior common to all account types. The specific account types will extend this abstract class and provide their own implementations for certain methods.

Code Implementation:

Abstract Class: BankAccount

Concrete Subclass: SavingsAccount

Concrete Subclass: CurrentAccount

Concrete Subclass: FixedDepositAccount

Using the Abstraction in BankSystem

Explanation:

- Abstraction: The BankAccount class serves as an abstract base class that defines the basic operations (deposit, withdraw, calculate interest, display account details) common to all bank accounts. The calculateInterest() method is abstract, forcing subclasses to provide their own implementation.

- SavingsAccount: Implements the calculateInterest() method specific to savings accounts, where interest is added to the balance based on a defined interest rate.

- CurrentAccount: Overrides the withdraw() method to account for overdraft limits and provides a specific implementation of calculateInterest() where no interest is applied.

- FixedDepositAccount: Implements the calculateInterest() method for fixed deposits, where interest is calculated based on the duration of the deposit.

This example demonstrates how abstraction allows you to define a common interface or behavior (via the abstract BankAccount class) while leaving the specifics to the subclasses, each of which implements the methods in a way that suits their particular needs.
