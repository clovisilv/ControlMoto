CREATE DataBase IF NOT EXISTS `controlmotoserver`;

DROP TABLE IF EXISTS `controlmotoserver`.`Usuario`;
CREATE TABLE `controlmotoserver`.`Usuario` (
    `UsuarioPk` INTEGER NOT NULL,
    `NomeUsuario` VARCHAR(150) NOT NULL,
    `Usuarioss` VARCHAR(30) NOT NULL,
    `Senhass` VARCHAR(6) NOT NULL,
    `TipoUsuario` VARCHAR(20) NOT NULL,
    `Status` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`UsuarioPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Endereco`;
CREATE TABLE `controlmotoserver`.`Endereco` (
    `EnderecoPk` INTEGER NOT NULL AUTO_INCREMENT,
    `Cep` VARCHAR(9) NULL,
    `Endereco` VARCHAR(200) NULL,
    `Bairro` VARCHAR(50) NULL,
    `Cidade` VARCHAR(50) NULL,
    `Estado` VARCHAR(2) NULL,
    PRIMARY KEY (`EnderecoPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Telefone`;
CREATE TABLE `controlmotoserver`.`Telefone` (
    `TelefonePK` INTEGER NOT NULL AUTO_INCREMENT,
    `TelefoneA` VARCHAR(14) NULL,
    `TelefoneB` VARCHAR(14) NULL,
    `CelularA` VARCHAR(14) NULL,
    `CelularB` VARCHAR(14) NULL,
    `FaxA` VARCHAR(14) NULL,
    `FaxB` VARCHAR(14) NULL,
    PRIMARY KEY (`TelefonePK`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Adiantamento`;
CREATE TABLE `controlmotoserver`.`Adiantamento` (
    `AdiantamentoPk` INTEGER NOT NULL,
    `CodSolicitante` VARCHAR(150) NOT NULL,
    `NomeSolicitante` VARCHAR(30) NOT NULL,
    `Valor` DECIMAL(8 , 2 ) NOT NULL,
    `TipoAdiantamento` VARCHAR(20) NOT NULL,
    `DataSolicitacao` DATE NOT NULL,
    `StatusSolicitacao` VARCHAR(20) NOT NULL,
    `ObsSolicitacao` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`AdiantamentoPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Motorista`;
CREATE TABLE `controlmotoserver`.`Motorista` (
    `MotoristaPk` VARCHAR(8) NOT NULL,
    `EnderecoMFk` INTEGER NULL,
    `TelefoneMFk` INTEGER NULL,
    `Nome` VARCHAR(150) NOT NULL,
    `Numero` VARCHAR(10) NULL,
    `Rg` VARCHAR(12) NOT NULL,
    `Cpf` VARCHAR(14) NOT NULL,
    `Habilitacao` VARCHAR(14) NOT NULL,
    `Categoria` VARCHAR(1) NOT NULL,
    `DataVencimento` DATE NOT NULL,
    `DataNascimento` DATE NOT NULL,
    `Banco` VARCHAR(50) NULL,
    `Agencia` VARCHAR(10) NULL,
    `Conta` VARCHAR(15) NULL,
    `Tipo` VARCHAR(10) NULL,
    `Comissao` NUMERIC(6 , 2 ) NULL,
    `DataCadastro` DATE NOT NULL,
    PRIMARY KEY (`MotoristaPk`),
    CONSTRAINT `EnderecoMFk` FOREIGN KEY (`EnderecoMFk`)
        REFERENCES Endereco (`EnderecoPk`),
    CONSTRAINT `TelefoneMFk` FOREIGN KEY (`TelefoneMFk`)
        REFERENCES Telefone (`TelefonePk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Cliente`;
CREATE TABLE `controlmotoserver`.`Cliente` (
    `ClientePk` INTEGER NOT NULL,
    `EnderecoCFk` INTEGER NULL,
    `TelefoneCFk` INTEGER NULL,
    `Nome` VARCHAR(150) NOT NULL,
    `Numero` VARCHAR(10) NULL,
    `Cnpj` VARCHAR(18) NULL,
    `IE` VARCHAR(15) NULL,
    `CCM` VARCHAR(20) NULL,
    `Site` VARCHAR(100) NULL,
    `Email` VARCHAR(100) NULL,
    `Contato` VARCHAR(50) NULL,
    `ValorHora` NUMERIC(6 , 2 ) NULL,
    `Taxa` NUMERIC(6 , 2 ) NULL,
    `DataCadastro` DATE NOT NULL,
    PRIMARY KEY (`ClientePk`),
    CONSTRAINT `EnderecoCFk` FOREIGN KEY (`EnderecoCFk`)
        REFERENCES Endereco (`EnderecoPk`),
    CONSTRAINT `TelefoneCFk` FOREIGN KEY (`TelefoneCFk`)
        REFERENCES Telefone (`TelefonePk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Fornecedor`;
CREATE TABLE `controlmotoserver`.`Fornecedor` (
    `FornecedorPk` INTEGER NOT NULL,
    `EnderecoFFk` INTEGER NULL,
    `TelefoneFFk` INTEGER NULL,
    `Nome` VARCHAR(150) NOT NULL,
    `Numero` VARCHAR(10) NULL,
    `Cnpj` VARCHAR(18) NULL,
    `IE` VARCHAR(15) NULL,
    `CCM` VARCHAR(20) NULL,
    `Site` VARCHAR(100) NULL,
    `Email` VARCHAR(100) NULL,
    `Contato` VARCHAR(50) NULL,
    `ValorHora` NUMERIC(6 , 2 ) NULL,
    `Taxa` NUMERIC(6 , 2 ) NULL,
    `DataCadastro` DATE NOT NULL,
    PRIMARY KEY (`FornecedorPk`),
    CONSTRAINT `EnderecoCFk` FOREIGN KEY (`EnderecoFFk`)
        REFERENCES Endereco (`EnderecoPk`),
    CONSTRAINT `TelefoneCFk` FOREIGN KEY (`TelefoneFFk`)
        REFERENCES Telefone (`TelefonePk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Ordem`;
CREATE TABLE `controlmotoserver`.`Ordem` (
    `OrdemPk` INTEGER NOT NULL,
    `ClienteOFk` INTEGER NOT NULL,
    `ValorHora` NUMERIC(6 , 2 ) NULL,
    `Taxa` NUMERIC(6 , 2 ) NULL,
    `MotoristaOFk` VARCHAR(8) NOT NULL,
    `DataInicio` DATE NULL,
    `HoraInicio` VARCHAR(5) NULL,
    `DataTermino` DATE NULL,
    `HoraTermino` VARCHAR(5) NULL,
    `TotalHoras` VARCHAR(5) NULL,
    `TotalCliente` NUMERIC(8 , 2 ) NULL,
    `TotalMotorista` NUMERIC(8 , 2 ) NULL,
    `ObsInicio` VARCHAR(2000) NULL,
    `ObsTermino` VARCHAR(2000) NULL,
    `DataVencimento` DATE NULL,
    `Status` VARCHAR(3) NOT NULL,
    `NumeroRPS` INTEGER NULL,
    `NumeroFatura` INTEGER NULL,
    PRIMARY KEY (`OrdemPk`),
    CONSTRAINT `ClienteOFk` FOREIGN KEY (`ClienteOFk`)
        REFERENCES Cliente (`ClientePk`),
    CONSTRAINT `MotoristaOFk` FOREIGN KEY (`MotoristaOFk`)
        REFERENCES Motorista (`MotoristaPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Empresa`;
CREATE TABLE `controlmotoserver`.`Empresa` (
    `EmpresaPk` INTEGER NOT NULL,
    `EnderecoEFk` INTEGER NULL,
    `TelefoneEFk` INTEGER NULL,
    `NomeEmpresa` VARCHAR(150) NOT NULL,
    `Cnpj` VARCHAR(18) NULL,
    `IE` VARCHAR(15) NULL,
    `CCM` VARCHAR(20) NULL,
    `Site` VARCHAR(100) NULL,
    `Email` VARCHAR(100) NULL,
    `DataCadastro` DATE NULL,
    PRIMARY KEY (`EmpresaPk`),
    CONSTRAINT `EnderecoEFk` FOREIGN KEY (`EnderecoEFk`)
        REFERENCES Endereco (`EnderecoPk`),
    CONSTRAINT `TelefoneEFk` FOREIGN KEY (`TelefoneEFk`)
        REFERENCES Telefone (`TelefonePk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `controlmotoserver`.`Faturar`;
CREATE TABLE `controlmotoserver`.`Fatura` (
    `FaturaPk` INTEGER NOT NULL,
    `DataFaturamento` DATE NULL,
    `NumeroRps` INTEGER NULL,
    `DataEmissaoNota` DATE NULL,
    `NumeroNotaFiscal` INTEGER NULL,
    `ClientePk` INTEGER NULL,
    `Nome` VARCHAR(150) NOT NULL,
    `OrdensFaturadas` VARCHAR(500) NOT NULL,
    `ValorHora` NUMERIC(6 , 2 ) NULL,
    PRIMARY KEY (`FaturaPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbCredor` (
    `CredorPk` INT NOT NULL AUTO_INCREMENT,
    `NomeCredor` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`CredorPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbDevedor` (
    `DevedorPk` INT NOT NULL AUTO_INCREMENT,
    `NomeDevedor` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`DevedorPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbContasPagar` (
    `ContasPagarPk` INT NOT NULL AUTO_INCREMENT,
    `CredorFk` INT NULL,
    `DtDocumento` DATE NULL,
    `EspecieDocumentoFk` VARCHAR(5) NULL,
    `NumDocumento` VARCHAR(10) NULL,
    `TpPagto` VARCHAR(3) NULL,
    `FormaPagto` INT NULL,
    `VlDocumento` DECIMAL(8 , 2 ) NULL,
    `DtVencimento` DATE NULL,
    `CodBarra` VARCHAR(80) NULL,
    `VlPagar` DECIMAL(8 , 2 ) NULL,
    `TpJuros` INT NULL,
    `VlJuros` DECIMAL(8 , 2 ) NULL,
    `CodTpCobrarJurosFk` VARCHAR(3) NULL,
    `TpMulta` INT NULL,
    `VlMulta` DECIMAL(8 , 2 ) NULL,
    `TipoDoTitulo` INT NULL,
    `DtQuitacao` DATE NULL,
    `Status` VARCHAR(45) NULL,
    PRIMARY KEY (`ContasPagarPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbContasReceber` (
    `ContasReceberPk` INT NOT NULL AUTO_INCREMENT,
    `DevedorFk` INT NULL,
    `DtDocumento` DATE NULL,
    `EspecieDocumentoFk` VARCHAR(5) NULL,
    `NumDocumento` VARCHAR(10) NULL,
    `TpPagto` VARCHAR(3) NULL,
    `FormaPagto` INT NULL,
    `VlDocumento` DECIMAL(8 , 2 ) NULL,
    `DtVencimento` DATE NULL,
    `CodBarra` VARCHAR(80) NULL,
    `VlReceber` DECIMAL(8 , 2 ) NULL,
    `TpJuros` INT NULL,
    `VlJuros` DECIMAL(8 , 2 ) NULL,
    `CodTpCobrarJurosFk` VARCHAR(3) NULL,
    `TpMulta` INT NULL,
    `VlMulta` DECIMAL(8 , 2 ) NULL,
    `TipoDoTitulo` INT NULL,
    `DtQuitacao` DATE NULL,
    `Status` VARCHAR(45) NULL,
    PRIMARY KEY (`ContasReceberPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbEspecieDocumento` (
    `EspecieDocumentoPk` VARCHAR(3) NOT NULL,
    `CodEspecDocumento` VARCHAR(5) NULL,
    `Descricao` VARCHAR(40) NULL,
    PRIMARY KEY (`EspecieDocumentoPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbFormaPagto` (
    `FormaPagtoPk` INT NOT NULL,
    `CodForma` INT NOT NULL,
    `Descricao` VARCHAR(40) NULL,
    PRIMARY KEY (`FormaPagtoPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbTipoPagto` (
    `TipoPagtoPk` VARCHAR(3) NOT NULL,
    `CodTpPagto` VARCHAR(3) NULL,
    `Descricao` VARCHAR(40) NULL,
    PRIMARY KEY (`TipoPagtoPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbSituacao` (
    `SituacaoPk` VARCHAR(3) NOT NULL,
    `Descricao` VARCHAR(40) NULL,
    `Utilizacao` VARCHAR(2) NULL,
    `Em_uso` INT NULL,
    PRIMARY KEY (`SituacaoPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbFormaCobrancaJuros` (
    `FormaCobrancaJurosPk` VARCHAR(3) NOT NULL,
    `CodFormaCobrancaJuros` VARCHAR(4) NULL,
    `Descricao` VARCHAR(40) NULL,
    PRIMARY KEY (`FormaCobrancaJurosPk`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbTarefas` (
   `TarefaPk` INT NOT NULL,
   `DescricaoTarefa` VARCHAR(50) NULL,
   PRIMARY KEY(`TarefaPk`) 
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `controlmotoserver`.`TbAgendamento` (
   `AgendaPk` INT NOT NULL,
   `DataAgendada` DATE NOT NULL,
   `HoraAgendada` TIME NOT NULL,
   `ClienteAFk` INT NOT NULL,
   `NomeCliente` VARCHAR(200) NOT NULL,
   `TarefaAFk` INT NOT NULL,
   `NomeTarefa` VARCHAR(50) NOT NULL,
   `StatusAgenda` VARCHAR(50) NOT NULL,
   `MotoristaAFk` VARCHAR(10) NULL,
   `NomeMotorista` VARCHAR(200) NULL,
   `NumeroOS` INT NULL,
   PRIMARY KEY(`AgendaPk`)
)
ENGINE = InnoDB;

CREATE TABLE `controlmotoserver`.`tbpreco`(
	`PrecoPk` INTEGER NOT NULL,
	`CodigoTbPreco`  VARCHAR(05) NOT NULL,
	`DescricaoTbPreco`  VARCHAR(30) NOT NULL,
	`SequenciaPreco` INTEGER NOT NULL,
	`ItemDescricaoPreco` VARCHAR(50) NULL,
	`ValorPreco` DECIMAL(8,2) NOT NULL,
PRIMARY KEY (`PrecoPk`)
)
ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1;

INSERT INTO `controlmotoserver`.`tbespeciedocumento`(`EspecieDocumentoPk`,`Descricao`,`CodEspecDocumento`)
VALUES(1,'Nota Fiscal Mercadoria','NFE-M');
INSERT INTO `controlmotoserver`.`tbespeciedocumento`(`EspecieDocumentoPk`,`Descricao`,`CodEspecDocumento`)
VALUES(2,'Nota Fisnal de Serviço','NFE-S');
INSERT INTO `controlmotoserver`.`tbespeciedocumento`(`EspecieDocumentoPk`,`Descricao`,`CodEspecDocumento`)
VALUES(3,'Cupom Fiscal','CUP-F');

INSERT INTO `controlmotoserver`.`tbformacobrancajuros`(`FormaCobrancaJurosPk`,`CodFormaCobrancaJuros`,`Descricao`)
VALUES(1,'AM','Ao mês');
INSERT INTO `controlmotoserver`.`tbformacobrancajuros`(`FormaCobrancaJurosPk`,`CodFormaCobrancaJuros`,`Descricao`)
VALUES(2,'AN','Ao ano');

INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(0,'À vista',0);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(1,'1 vezes (30 dias)',1);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(2,'2 vezes (60 dias)',2);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(3,'3 vezes (90 dias)',3);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(4,'4 vezes (120 dias)',4);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(5,'5 vezes (150 dias)',5);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(6,'6 vezes (180 dias)',6);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(7,'7 vezes (210 dias)',7);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(8,'8 vezes (240 dias)',8);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(9,'9 vezes (270 dias)',9);
INSERT INTO `controlmotoserver`.`tbformapagto`(`FormaPagtoPk`,`Descricao`,`CodForma`)
VALUES(10,'10 vezes (300 dias)',10);

INSERT INTO `controlmotoserver`.`tbsituacao`(`SituacaoPk`,`Descricao`,`Utilizacao`,`Em_uso`)
VALUES(1,'A vencer','CP','NULL');
INSERT INTO `controlmotoserver`.`tbsituacao`(`SituacaoPk`,`Descricao`,`Utilizacao`,`Em_uso`)
VALUES(2,'A Vencer','CR','NULL');
INSERT INTO `controlmotoserver`.`tbsituacao`(`SituacaoPk`,`Descricao`,`Utilizacao`,`Em_uso`)
VALUES(3,'Vencido','CP','Null');
INSERT INTO `controlmotoserver`.`tbsituacao`(`SituacaoPk`,`Descricao`,`Utilizacao`,`Em_uso`)
VALUES(4,'Vencido','CR','NULL');
INSERT INTO `controlmotoserver`.`tbsituacao`(`SituacaoPk`,`Descricao`,`Utilizacao`,`Em_uso`)
VALUES(5,'Quitado','CP','NULL');
INSERT INTO `controlmotoserver`.`tbsituacao`(`SituacaoPk`,`Descricao`,`Utilizacao`,`Em_uso`)
VALUES(6,'Quitado','CR','NULL');

INSERT INTO `controlmotoserver`.`tbtipopagto`(`TipoPagtoPk`,`Descricao`,`CodTpPagto`)
VALUES(1,'Dinheiro','Din');
INSERT INTO `controlmotoserver`.`tbtipopagto`(`TipoPagtoPk`,`Descricao`,`CodTpPagto`)
VALUES(2,'Cheque','Ch');
INSERT INTO `controlmotoserver`.`tbtipopagto`(`TipoPagtoPk`,`Descricao`,`CodTpPagto`)
VALUES(3,'Débito','Déb');
INSERT INTO `controlmotoserver`.`tbtipopagto`(`TipoPagtoPk`,`Descricao`,`CodTpPagto`)
VALUES(4,'Cartão Crédito','Car');
INSERT INTO `controlmotoserver`.`tbtipopagto`(`TipoPagtoPk`,`Descricao`,`CodTpPagto`)
VALUES(5,'Boleto','Bol');

INSERT INTO `controlmotoserver`.`TbTarefas`(`TarefaPk`,`DescricaoTarefa`)
VALUES(1,'Moto');
INSERT INTO `controlmotoserver`.`TbTarefas`(`TarefaPk`,`DescricaoTarefa`)
VALUES(2,'Fiorino');
INSERT INTO `controlmotoserver`.`TbTarefas`(`TarefaPk`,`DescricaoTarefa`)
VALUES(3,'Kombi');

INSERT INTO `controlmotoserver`.`tbpreco`(`PrecoPk`, `CodigoTbPreco`, `DescricaoTbPreco`, `SequenciaPreco`, `ItemDescricaoPreco`, `ValorPreco`)
VALUES(1, 'TESTE', 'Teste de preço', 1, 'Moto', '12.90');
INSERT INTO `controlmotoserver`.`tbpreco`(`PrecoPk`, `CodigoTbPreco`, `DescricaoTbPreco`, `SequenciaPreco`, `ItemDescricaoPreco`, `ValorPreco`)
VALUES(2, 'TESTE', 'Teste de preço', 2, 'Pick-up', '15.80');
INSERT INTO `controlmotoserver`.`tbpreco`(`PrecoPk`, `CodigoTbPreco`, `DescricaoTbPreco`, `SequenciaPreco`, `ItemDescricaoPreco`, `ValorPreco`)
VALUES(3, 'TESTE', 'Teste de preço', 3, 'Kombi', '19.20');