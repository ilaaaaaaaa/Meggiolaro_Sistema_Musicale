# Esercizio: sistema musicale
## Consegna
In questo esercizio dovrai realizzare un sistema per gestire canzoni e artisti musicali. L'applicazione combinerà l'uso di API esterne per recuperare dati musicali con un database locale dove salvare le informazioni degli artisti preferiti, dopo aver fatto partire il jar in allegato, aver letto attentamente la documentazione delle API e aver creato le richieste corrette in Postman per familiarizzare con gli endpoint disponibili.

Il programma funzionerà attraverso un menu a console che permetterà agli utenti di navigare tra diverse funzionalità. Potranno consultare cataloghi di canzoni e artisti provenienti da servizi esterni, ma anche gestire una collezione personale salvata nel database locale.

### DA IMPLEMENTARE
Il sistema si basa su alcune classi principali che dovrai sviluppare:

#### MAIN 
Gestirà l'interfaccia utente attraverso un menu interattivo. Gli utenti potranno scegliere tra diverse opzioni come visualizzare canzoni, cercare artisti specifici, aggiungerne di nuovi o modificare quelli esistenti.

#### API 
Sarà affidata a una classe dedicata che si occuperà di comunicare con servizi esterni per recuperare informazioni su canzoni e artisti. Questa classe dovrà anche permettere di aggiungere, modificare ed eliminare artisti attraverso le API.

#### DATABASE 
Utilizzerà il pattern Singleton per garantire una sola connessione attiva. Servirà principalmente per salvare gli artisti che l'utente decide di conservare nella sua collezione personale.

#### ENTITA' 
Rappresenteranno canzoni e artisti con i loro attributi caratteristici come titoli, durate, nomi, generi musicali e paesi di origine.

### FUNZIONALITA' RICHIESTE
Il menu dovrà offrire opzioni per:
- Esplorare il catalogo completo di canzoni disponibili
- Cercare canzoni specifiche tramite ID
- Consultare l'elenco degli artisti disponibili
- Visualizzare dettagli di artisti specifici con possibilità di salvarli nel database
- Aggiungere nuovi artisti al sistema
- Modificare informazioni di artisti esistenti
- Eliminare artisti dal catalogo
- Consultare la collezione locale salvata nel database