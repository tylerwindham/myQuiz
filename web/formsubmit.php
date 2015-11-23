<?php

$myFile = "testFile.txt";
$fileWrite = $_POST['datastring'];
	  echo " here ". $fileWrite . " there ";
if(isset($_POST['datastring']) && !empty($_POST['datastring'])) {
      $fileWrite = $_POST['datastring'];
	  echo " after ". $fileWrite . " before ";

}
if($fileWrite) {
	echo "deez";
    $fh = fopen($myFile, 'a') or die("can't open file"); //Make sure you have permission
    fwrite($fh, $fileWrite);
    fclose($fh);
    //exec('/your/command /dev/null 2>/dev/null &');
} 

//echo " done submitting "

?>
