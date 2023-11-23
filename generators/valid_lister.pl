use File::Find;

open($outfile, '>', "./src/test/resources/valid_eg.txt");
find(\&record_example, $ARGV[0]);
print $outfile ("fin.");
close($outfile);

sub record_example {
  if (-f) {
    open(EFH, '<', $_);
    print $outfile ($File::Find::name . "\n");
    print $outfile (scalar(readline(EFH)));
    close(EFH);
  }
}

