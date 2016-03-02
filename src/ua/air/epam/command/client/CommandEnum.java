package ua.air.epam.command.client;

import ua.air.epam.command.ActionCommand;
import ua.air.epam.command.AddEmployeeCommand;
import ua.air.epam.command.AddFlightCommand;
import ua.air.epam.command.BackToAdminCommand;
import ua.air.epam.command.BackToDispatcherCommand;
import ua.air.epam.command.BackToStaffCommand;
import ua.air.epam.command.ChangeEmployeeCommand;
import ua.air.epam.command.CompleteFlightCommand;
import ua.air.epam.command.DeleteFlightCommand;
import ua.air.epam.command.FormTeamCommand;
import ua.air.epam.command.LangCommand;
import ua.air.epam.command.LoginCommand;
import ua.air.epam.command.LogoutCommand;
import ua.air.epam.command.ManageStaffCommand;
import ua.air.epam.command.ModifyEmployeeCommand;
import ua.air.epam.command.RegisterEmployeeCommand;
import ua.air.epam.command.TeamCommand;
/**
 * 
 * @author Alex
 * Command wich are avaible for executing
 */
public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LANG {
		{
			this.command = new LangCommand();
		}
	},
	TEAM {
		{
			this.command = new TeamCommand();
		}
	},
	FORMTEAM {
		{
			this.command = new FormTeamCommand();
		}
	},
	ADDEMPLOYEE {
		{
			this.command = new AddEmployeeCommand();
		}
	},
	MODIFYEMPLOYEE {
		{
			this.command = new ModifyEmployeeCommand();
		}
	},
	CHANGEEMPLOYEE {
		{
			this.command = new ChangeEmployeeCommand();
		}
	},
	COMPLETEFLIGHT {
		{
			this.command = new CompleteFlightCommand();
		}
	},
	DELETEFLIGHT {
		{
			this.command = new DeleteFlightCommand();
		}
	},
	ADDFLIGHT {
		{
			this.command = new AddFlightCommand();
		}
	},
	BACKTODISPATCHER {
		{
			this.command = new BackToDispatcherCommand();
		}
	},
	BACKTOADMIN {
		{
			this.command = new BackToAdminCommand();
		}
	},
	BACKTOSTAFF {
		{
			this.command = new BackToStaffCommand();
		}
	},
	MANAGESTAFF {
		{
			this.command = new ManageStaffCommand();
		}
	},
	REGISTEREMPLOYEE {
		{
			this.command = new RegisterEmployeeCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}