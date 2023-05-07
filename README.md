# BankingSystem

## Colectii utilizate 

1. HashMap< String, Customer > in SystemFacade.java
2. ArrayList< Transaction > in SystemFacade.java, Customer.java
3. Transaction[] in SystemFacade.java, TransactionService.java
4. (Sortata) TreeSet< Transaction > in Customer.java

## Servicii scriere / citire baza de date (Data Layer)

1. AccountDL
2. CardDL
3. CustomerDL
4. TransactionDL

## Servicii operatii sistem

1. AccountService
2. CardService
3. CustomerService
4. TransactionService

## Interfata Utilizator

1. Main
2. SystemFacade

## Serviciu Audit / CSV

1. AuditService

## Actiuni

### Cont banca
1. Creare cont nou -> create_base_account
2. Stergere cont -> delete_account
3. Actualizare informatii cont -> update_account
4. Citire informatii cont -> read_account
### Client banca
5. Creare client persoana fizica -> create_natural_customer
6. Creare client persoana juridica -> create_artificial_customer
7. Stergere client -> delete_customer
8. Actualizare informatii client -> update_customer
9. Citire informatii client -> read_customer
### Tranzactii banca 
10. Depozitare bani in cont -> create_deposit_transaction
11. Extragere bani din cont -> create_withdraw_transaction
12. Transfer intre conturi -> create_transfer_transaction
13. Citire detalii tranzactie -> read_transaction
14. Adaugare tranzactii neefectuate pe client -> create_***_transaction
15. Efectuarea tranzactiilor facute de client si stocarea celor reusite -> store_pending_transactions
16. Stergere tranzactie -> delete_transaction
### Card banca 
17. Creare card credit -> create_credit_card
18. Creare card debit -> create_debit_card
19. Citire detalii card -> read_card
20. Actualizare informatii card -> update_card
21. Stergere card-> delete_card

## Obiecte

### Cont 
1. Account
2. AccountFactory
3. AccountService
4. AccountDL (interfata pentru baza de date / dictionar in-memory)
### Client
5. Artificial 
6. Natural 
7. CustomerService
8. CustomerDL (interfata pentru baza de date / dictionar in-memory)
### Tranzactie
9. Deposit
10. Withdraw
11. TransactionService
12. TransactionDL (interfata pentru baza de date / dictionar in-memory)
### Card 
13. CreditCard 
14. DebitCard 
15. CardService 
16. CardDL (interfata pentru baza de date / dictionar in-memory)
### Exceptii
17. AccountException
18. CustomerException
19. TransactionException
20. CardException
### Utils
21. Demo