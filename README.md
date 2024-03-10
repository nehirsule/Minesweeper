Açıklama

MineSweeper, klasik bir bilgisayar oyunudur. Oyunda amacınız, mayın olmayan tüm kutuları açmak ve hiçbir mayına dokunmadan oyunu tamamlamaktır. 
Her kutunun etrafındaki mayın sayısı belirtilir. Oyun tahtasında herhangi bir kutuyu açmak için koordinatları girmeniz gerekir.

Oyun Kuralları:

Oyun metin tabanlıdır.
Proje için çift boyutlu diziler kullanılmıştır. Metodlar kullanılarak kod tekrarı önlenmiştir.

Kullanılan metodlar:
prepareBoard(): Oyun tahtasının başlangıç durumuyla doldurulmasını sağlar.
placeMines(): Mayınları rastgele yerleştirir.
calculateNeighborMines(): Açılan kutuda mayın yoksa, komşularındaki mayın sayısını hesaplar.
validBox(int row, int column): Verilen satır ve sütunun tahtanın sınırları içinde olup olmadığını kontrol eder.
play(): Oyunun oynanmasını sağlar. Kullanıcıdan girdi alır, kutuyu kontrol eder ve oyunun sonlanmasını kontrol eder.
printBoard(): Oyun tahtasını kullanıcıya gösterir.
gameOver(): Oyunun sonlanmasına neden olan durumda tüm mayınları ve oyun tahtasını kullanıcıya gösterir.
main(String[] args): Oyunun başlatılmasını ve kullanıcı girdilerinin alınmasını sağlar.


Matris boyutunu yani satır ve sütun sayısını kullanıcı belirlemektedir. Minimum 2x2 boyutunda matris grişine izin verilir, 2x2 den küçük değer girişlerinde kullanıcıya uyarı verilir ve tekrar satır, sütun girmesi istenir.

Diziye ait eleman sayısının çeyreği (elemanSayisi / 4) kadar rastgele mayın yerleştirilir. Örneğin dizi 4x3 boyutunda ise eleman sayısı (satırSayısı * sütunSayısı) formülü ile hesaplanmalı ve boyutu 12 olacaktır. 
Bu durumda mayın sayısı 12 / 4 = 3 adet olmalıdır. 3'ten az ya da çok olmamalıdır.

Oyuncuya gösterilen haritada hiç açılmamış kutucukları "-" sembolü ile gösterilir.Mayınlar "*" sembolü ile gösterilir.

Kullanıcı matris üzerinden bir nokta seçer, nokta seçimi için satır ve sütun değerlerini girmesi istenilir.
Seçilen noktanın dizinin sınırları içerisinde olup olmadığı kontrol edilir ve koşul sağlanmazsa konsolda uyarı metni yazdırılıp kullanıcıdan tekrar yeni koordinatlar istenir.
Daha önce girilmiş bir koordinat girildiğinde kullanıcıya "bu koordinat daha önce seçildi, başka bir koordinat girin" şeklinde uyarı gösterilir ve yeni giriş yapması istenir.


Kullanıcının girdiği noktada mayın var ise oyunu kaybeder.
Kullanıcının girdiği noktada mayın yok ise, noktanın etrafındaki tüm komşu konumlara bakılır (sağı, solu, yukarısı, aşağısı, sol üst çapraz, sağ üst çapraz, sağ alt çapraz, sol alt çapraz) ve bu komşu noktalardaki mayınların sayısının toplamı kullanıcının girmiş olduğu koordinata yazılır. Noktaya değen herhangi bir mayın yok ise "0" değeri yazılmalıdır. Farklı değer ve sembol kullanılmaz.

Kullanıcı hiç bir mayına basmadan tüm noktaları açarsa oyunu kazanmalıdır. Bu durumda da kazandığı mesajı gösterilir.

