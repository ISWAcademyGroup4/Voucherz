CREATE TABLE ActivationToken(
	ActId INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	Email NVARCHAR(50) NOT NULL,
	ActivationToken NVARCHAR(50) NOT NULL,
	ExpiryDate DateTime NOT NULL
)

GO

CREATE PROCEDURE uspCreateActivationToken
(
	@email NVARCHAR(50),
	@activationToken NVARCHAR(50),
	@expirydate DATETIME
)
AS

SET NOCOUNT ON

BEGIN TRANSACTION uspCreateActivationToken

INSERT INTO [dbo].ActivationToken(Email,ActivationToken,ExpiryDate) VALUES(@email, @activationToken, @expirydate)

COMMIT