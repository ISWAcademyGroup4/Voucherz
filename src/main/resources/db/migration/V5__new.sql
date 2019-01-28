CREATE PROCEDURE uspTokenGen
(
	@email NVARCHAR(50),
	@token NVARCHAR(50),
	@expirydate DATETIME
)
AS

SET NOCOUNT ON

BEGIN TRANSACTION uspTokenGen

INSERT INTO [dbo].PasswordReset(Email,ResetToken,ExpiryDate) VALUES(@email, @token, @expirydate)

COMMIT