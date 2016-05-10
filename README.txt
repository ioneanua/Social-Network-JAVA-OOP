TEMA 1 - POO 

Ioneanu Andrei - 324 CA

Social Network

Task 1 : Topologia retelei
	-am avut de implementat functinalitati de baza alea retelei sociale: adaugare
	stergere de utilizator ; adaugare si stergere de relatii de prietenie intre
	doi utilizatori , etc;
	-cu ajutorul file parser-ului primit am initializat o linie String numita comenzi
	care citea linie cu linie din fisier pana ce acesta dadea de null;
	-daca primul cuvant din linie este :
			-add , transformama din string in int id-ul unui utilizator , verificam 		
			daca se afla in linsta de utilizatori , iar daca nu se afla il adaugam;
			-remove , verificam (prin id) daca utilizatorul se afla in sistem , iar daca se afla
			il stergem atat din lista de utilizatori , cat si din listele de prieteni ale utilizatorilor
			in care se afla;
			-friend , cu ajutorul unor metode create de mine , verificam prima data daca acestia se afla
			in sistem , verificam sa vedem daca deja sunt prieteni , iar daca nu sunt , adaugam user cu id1 
			in lista de prieteni a user-ului cu id2 si invers;
			-unfriend , verificam daca userul cu id1 este in lista de prieteni a userului cu id2 , iar daca 
			este , il stergem si invers;iar daca nu se afla afisam "inexistent" ;
			-print (am facut si o metoda bubble sort care sorteaza utilizatorii intainte de afisare);
			network- cu un for (foreach)parcurgem tot arrayist-ul de useri si il afisam
			user - verificam daca utilizatorul cu id ul respectiv se afla in sistem , iar daca se afla
			il afisam impreuna cu id -urile prietenilor 


Task 2 : Comunitati
	- am definit o comunitate ca fiind o componenta conexa , sau un grup de utilizatori izolati ;
	- pentru a afisa aceste comunitati ne-a fost indicat sa folosim un algoritm de parcurgere in adancime , 
		mai precis DFS
	- am creat o metoda DFS , care a primit ca parametrii un vector boolean ca sa tinem minte 
		daca a fost sau nu vizitat un user 
	pentru a nu mai trece prin el , un user de strat , si un arrayist de int-uri (community) , 
	in care adaugam , in urma parcurgerii recursive , comunitatea
	, adica elementele grafului conex , in ordinea parcurgerii ; in main facand acesta metoda 
	intr un foreach care trece prin fiecare user , dar si prin
		prietenii fiecaruia;
		
Task 3 : Gradul de socializare 
	-trebuie sa vedem cat de "puternica" este o astfel de comunitate , adica sa vedem diametrul
	(lungimea celui mai lung drum) cel mai mare al comunitaii la care ne referim ; 
	pentru asta am avut nevoie , din nou , de un algoritm de parcurgere , de aceasta data in latime;
	am retinut de fiecare data distanta celei mai luni cai descoperite (lungimea am determinat-o facand 
	adunarea intre nodurile intermediare);
	dupa ce am obtinut diametrul comunitatii , am calculat ,pe baza formulei date , 
	gradul de socializre ;am realizat metoda BFS;
	- cand primimt ca al doilea argument dupa print , strength , verificam prima data daca utilizatorul 
	cu id-ul dat se afla in sistem , daca se afla, prima data aplicam DFS pentru a afla comunitatiile 
	dupa care le bagam intr un arrayist de useri pentru a aplica corect metoda BFS
	
								





















































































































