# BankingSystem

## Actiuni

### Cont banca
1. Creare cont nou
2. Stergere cont 
3. Actualizare informatii cont 
4. Citire informatii cont 
### Client banca
5. Creare client persoana fizica
6. Creare client persoana juridica 
7. Stergere client 
8. Actualizare informatii client 
9. Citire informatii client 
### Tranzactii banca
10. Depozitare bani in cont 
11. Extragere bani din cont 
12. Transfer intre conturi
13. Citire detalii tranzactie
14. Adaugare tranzactii neefectuate pe client 
15. Efectuarea tranzactiilor facute de client si stocarea celor reusite
### Card banca 
16. Creare card credit
17. Creare card debit 
18. Citire detalii card 
19. Actualizare informatii card 
20. Stergere card

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