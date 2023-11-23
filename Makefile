all:
	sbt compile assembly

test:
	sbt test

clean:
	sbt clean && rm -rf wacc-15-compiler.jar

resources:
	perl separate_examples_syntax.pl wacc_examples/valid/
	perl separate_examples_semantic.pl wacc_examples/valid/ 
	perl separate_examples_syn_i.pl wacc_examples/invalid/syntaxErr/
	perl separate_examples_sem_i.pl wacc_examples/invalid/semanticErr/
	perl backend_tests.pl wacc_examples/valid/

.PHONY: all clean
